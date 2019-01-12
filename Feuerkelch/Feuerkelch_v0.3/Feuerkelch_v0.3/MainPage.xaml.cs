using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using System.Windows.Media.Imaging;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework;
using System.IO;
using System.Windows.Navigation;
using System.Xml.Linq;
using System.IO.IsolatedStorage;
using Microsoft.Phone.Shell;


namespace Feuerkelch_v0._3
{
    public partial class MainPage : PhoneApplicationPage
    {
        //spieler-array
        String[] players;
        //aktuelle position im array
        int currentPlayerPosition=0;
        //name des aktuellen spielers
        String currentPlayer="";

        //umgedrehte karte anlegen
        Card backsideCard = new Card();
        //aktuelle karte
        Card currentCard;
        //liste mit allen karten
        List<Card> karten= new List<Card>();

        //aktuelle rundenzahl, bei neuem spiel auf 1
        int currentRound;
        //spiel gewonnen?
        bool isWon = false;
        //spiel gestartet?
        bool isStarted = false;
        //anzahl der bisher gezogenen könige
        int kings;

        //falls instanz für spielstatus angelegt wird:
        //Game gameDAO;

        //index des aktuellen pivot für seitenwechsel
        int lastPage=0;

        //settings für isolatedStorage
        IsolatedStorageSettings gameTo;

        // Konstruktor
        public MainPage()
        {
            InitializeComponent();

            gameTo = IsolatedStorageSettings.ApplicationSettings;

            // Datenkontext des Listenfeldsteuerelements auf die Beispieldaten festlegen
            DataContext = App.ViewModel;
            this.Loaded += new RoutedEventHandler(MainPage_Loaded);
        }

        // Daten für die ViewModel-Elemente laden
        private void MainPage_Loaded(object sender, RoutedEventArgs e)
        {
            if (!App.ViewModel.IsDataLoaded)
            {
                App.ViewModel.LoadData();
            }
        }


        //methoden für pivotItem START

        //spieler hinzufügen
        private void imageAddPlayer_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            //max 10
            if (PlayerList.Items.Count > 9)
            {
                LabelMaxPlayers.Opacity = 1;
            }
            else
            {
                PlayerList.Items.Add(textBoxSpielerName.Text);

                //start button aktivieren wenn genug spieler
                if (PlayerList.Items.Count > 1)
                {
                    ButtonPlay.IsEnabled = true;
                }
                //inhalt für textfeld zurücksetzen
                textBoxSpielerName.Text = "Spielername";
            }
        }

        //in textfeld spielername klicken
        private void textBoxSpielerName_GotFocus(object sender, RoutedEventArgs e)
        {
            //Inhalt löschen
            textBoxSpielerName.Text = "";
        }

        //spieler löschen
        private void imageDeletePlayer_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            PlayerList.Items.Remove(PlayerList.SelectedItem);
            //StartButton deaktivieren falls zu wenige Spieler
            if (PlayerList.Items.Count < 2)
            {
                ButtonPlay.IsEnabled = false;
            }
        }

        //spiel (neu-)starten
        private void ButtonPlay_Click(object sender, RoutedEventArgs e)
        {
            //neues Spiel initialisieren
            karten.Clear();
            isWon = false;
            kings = 0;
            currentRound = 1;
            TextblockKings.Text = "0";

            //aktuelle Karte auf verdeckte setzen
            currentCard = backsideCard;
            if (!ImageCurrentCard.Source.Equals("/Feuerkelch_v0.3;component/Images/aa_backside.png"))
            {
                BitmapImage nextSource = new BitmapImage(new Uri("/Feuerkelch_v0.3;component/Images/aa_backside.png", UriKind.Relative));
                ImageCurrentCard.Source = nextSource;
            }
            manualContent.Text = currentCard.getManual();

            //array mit spielern füllen
            players = new String[PlayerList.Items.Count];
            for (int i = 0; i < PlayerList.Items.Count; i++)
            {
                players[i] = (String)PlayerList.Items.ElementAt(i);
            }

            //ersten spieler setzen
            currentPlayerPosition = 0;
            currentPlayer = players[currentPlayerPosition];
            textBlockCurrentPlayer.Text = currentPlayer;           

            //liste karten füllen
            for (int a = 7; a < 15; a++)
            {
                for (int b = 0; b < 4; b++)
                {
                    String farbe = "farbe";
                    switch (b)
                    {
                        case 0: farbe = "h";
                            break;
                        case 1: farbe = "d";
                            break;
                        case 2: farbe = "s";
                            break;
                        case 3: farbe = "c";
                            break;
                    }
                    karten.Add(new Card(a, farbe));
                }
            }
            //bool für gestartet setzen
            isStarted = true;

            //startbutton deaktivieren
            ButtonPlay.IsEnabled = false;

            //wechsel zur spielseite
            MainPivot.SelectedItem = game;

            //Spiel speichern
            saveGame();
        }


        //methoden für pivotItem SPIEL
 
        //nächsten spieler setzen
        private void setNextPlayer()
        {
            if (currentPlayerPosition+1 >= players.Length)
            {
                currentPlayerPosition = 0;
            }
            else 
            {
                currentPlayerPosition += 1;
            }
            currentPlayer = players[currentPlayerPosition];
        }

        //nächste karte ziehen
        private Card setNextCard()
        {
            Random zufallszahl = new Random();
            int i = zufallszahl.Next(1, 33 - currentRound);
            Card karte=karten.ElementAt(i);
            karten.RemoveAt(i);
            return karte;
        }

        //nächster zug - klick auf bild der karte
        private void ImageCurrentCard_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            getGame();
            
            if (!isWon&&isStarted)
            {
                if (currentCard == backsideCard)
                {
                    //nächste Karte ziehen
                    currentCard = setNextCard();
                    BitmapImage nextSource = new BitmapImage(new Uri(currentCard.getSource(), UriKind.Relative));
                    ImageCurrentCard.Source = nextSource;
                    
                    //@todo: hilfetext aus datei lesen
                    //neuen Hilfetext einblenden
                    manualContent.Text = currentCard.getManual();
                
                    //Rundenzahl inkrementieren
                    currentRound++;

                    //auf König und gewonnen prüfen
                    if (currentCard.getNr() == 13)
                    {
                        //könig getroffen
                        kings++;
                        //anzahl könige in textblock setzen
                        TextblockKings.Text = kings.ToString();

                        //gewonnen
                        if (kings >= 4)
                        {
                            isWon = true;
                            isStarted = false;
                            manualContent.Text = "Trink den Feuerkelch!";
                            //sounds:zonk 
                            Stream streamZonk = TitleContainer.OpenStream("Sounds/Zonk.wav");
                            SoundEffect effectZonk = SoundEffect.FromStream(streamZonk);
                            FrameworkDispatcher.Update();
                            effectZonk.Play();

                            //startbutton wieder aktivieren
                            ButtonPlay.IsEnabled = true;
                            //@todo: button für wechsel zur startseite

                        }
                    }
                }
                else
                {
                    //wechsel zum nächsten Spieler + anzeige
                    setNextPlayer();
                    textBlockCurrentPlayer.Text = currentPlayer;

                    //umgedrehte Karte ziehen
                    currentCard = backsideCard;
                    BitmapImage nextSource = new BitmapImage(new Uri(currentCard.getSource(), UriKind.Relative));
                    ImageCurrentCard.Source = nextSource;

                    //neuen Hilfetext einblenden
                    manualContent.Text = currentCard.getManual();
                }
            }
            saveGame();
        }

        //auf hilfeseite springen
        private void ApplicationBarIconButton_Click(object sender, EventArgs e)
        {
            //spielstatus in static-klasse speichern
            saveGame();

            //aktuelle Seite merken
            lastPage = MainPivot.SelectedIndex;

            //auf Extra-seite für anleitung springen
            string parameter = MainPivot.SelectedIndex.ToString();
            NavigationService.Navigate(new Uri(string.Format("/PageManual.xaml?parameter={0}", parameter), UriKind.Relative));

        }

        //spiel speichern
        public void saveGame()
        {
            //spielstatus in static-klasse speichern
            Game game= new Game(players, currentPlayerPosition, currentPlayer, currentCard, karten, currentRound, isWon, isStarted, kings);

            //spielstatus in global speichern
            PhoneApplicationService.Current.State["game"] = game;

            if (IsolatedStorageSettings.ApplicationSettings.Contains("completeGame"))
            {
                gameTo["completeGame"] = game;
            }
            else
            {
                gameTo.Add("completeGame", game);
            }

            gameTo.Save();

            /*
            //anzPlayers
            gameTo.Add("anzPlayers", players.Length);
            //players
            for (int i = 0; i < players.Length; i++)
            {
                gameTo.Add("pNr"+i, Convert.ToString(players[i]));
            }
            //currentPlayerPosition
            gameTo.Add("currentPlayerPosition", currentPlayerPosition+"");
            //currentPlayer
            gameTo.Add("currentPlayer", currentPlayer);

            //currentCard
            gameTo.Add("currentCardNr", Convert.ToString(currentCard.getNr()));
            gameTo.Add("currentCardManual", currentCard.getManual());
            gameTo.Add("currentCardSource", currentCard.getSource());

            //karten
            int a = 0;
            foreach (Card karte in karten)
            {
                gameTo.Add("karte" + a + "Nr", karte.getNr() + "");
                gameTo.Add("karte" + a + "Manual", karte.getManual());
                gameTo.Add("karte" + a + "Source", karte.getSource());
                a++;
            }
            //anzKarten
            gameTo.Add("anzKarten", Convert.ToString(a));
            //currentRound
            gameTo.Add("currentRound", Convert.ToString(currentRound));
            //isWon
            gameTo.Add("isWon", isWon.ToString());
            //isStarted
            gameTo.Add("isStarted", isStarted.ToString());
            //kings
            gameTo.Add("kings", Convert.ToString(kings));
              
           */
        }

        public Game loadGameFromIsolatedStorage(){

            /*
            //players
           String[] players=new String[int.Parse((String)gameTo["anzPlayers"])];
           for (int a = 0; a < int.Parse((String)gameTo["anzPlayers"]); a++)
           {
               players[a] = (String)gameTo["pNr" + a];
           }

           //currentPlayerPosition
            int currentPlayerPosition=int.Parse((String)gameTo["currentPlayerPosition"]);
            //currentPlayer
            String currentPlayer=(String)gameTo["currentPlayer"];

            //currentCard
            Card currentCard = new Card();
            currentCard.setNr(int.Parse((String)gameTo["currentCardNr"]));
            currentCard.setSourceDirectly((String)gameTo["currentCardSource"]);
            currentCard.setManual();

            //karten
            List<Card> karten = new List<Card>();
            for (int kartenAnz = 0; kartenAnz < int.Parse((String)gameTo["anzKarten"]);kartenAnz++ )
            {
                Card karte = new Card();
                karte.setNr(int.Parse((String)gameTo["karte"+kartenAnz+"Nr"]));
                karte.setSourceDirectly((String)gameTo["karte" + kartenAnz + "Source"]);
                karte.setManual();

                karten.Add(karte);
            }

            //currentRound
            int currentRound = int.Parse((String)gameTo["currentRound"]);
            //isWon
            bool isWon=Convert.ToBoolean(gameTo["isWon"]);
            //isStarted 
            bool isStarted = Convert.ToBoolean(gameTo["isStarted"]);

            int kings=int.Parse((String)gameTo["kings"]);
              
             


            return new Game(
                 players,
                 currentPlayerPosition,
                 currentPlayer,
                 currentCard,
                 karten,
                 currentRound,
                 isWon,
                 isStarted,
                 kings);*/
            return (Game)gameTo["completeGame"];
        }

        //spiel laden
        public void getGame()
        {
            Game game = (Game)PhoneApplicationService.Current.State["game"];

            game = loadGameFromIsolatedStorage();

            //aus globals spielstatus laden 
            //gameDAO = Game(PhoneApplicationService.Current.State["game"]);

            //werte holen aus static-klasse
            this.players = game.getPlayers();
            this.currentPlayerPosition = game.getCurrentPlayerPosition();
            this.currentPlayer = game.getCurrentPlayer();
            this.currentCard = game.getCurrentCard();
            this.karten = game.getKarten();
            this.currentRound = game.getCurrentRound();
            this.isWon = game.getIsWon();
            this.isStarted = game.getIsStarted();
            this.kings = game.getKings();

            //anzeige setzen
            textBlockCurrentPlayer.Text = currentPlayer;
            for (int z = 0; z < players.Count(); z++)
            {
                PlayerList.Items.Add(players[z]);
            }
            //neuen Hilfetext einblenden
            manualContent.Text = currentCard.getManual();
            //anzahl könige in textblock setzen
            TextblockKings.Text = kings.ToString();
            //umgedrehte Karte ziehen
            String source = currentCard.getSource();
            BitmapImage nextSource = new BitmapImage(new Uri(source, UriKind.Relative));
            ImageCurrentCard.Source = nextSource;

            //buttonPlay (de-)aktivieren
            if (isWon)
            {
                ButtonPlay.IsEnabled = true;
            }
            else
            {
                ButtonPlay.IsEnabled = false;
            }
        }

        public static PivotItem SelectedItem { get; set; }


        //überschriebene funktion für navigation
        //stellt den ursprünglichen Spielstand wieder her
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);
            try
            {
                //was war letzte seite?
                string newparameter = this.NavigationContext.QueryString["parameter"];
                lastPage = int.Parse(newparameter);
                //auf aktuelle seite springen
                MainPivot.SelectedIndex = lastPage;

                //spiel laden
                getGame();
            }
            //beim start der anwendung wird hier der fehler geworfen, 
            //da der parameter nicht mitgegeben wird
            catch (Exception exec)
            {
                if (PhoneApplicationService.Current.State["reactivated"].Equals("true"))
                {
                    getGame();
                }
                
                //auf startseite gehen
                MainPivot.SelectedItem = welcome;               
                
            }
        }

        private void ApplicationBarIconButton_Click_1(object sender, EventArgs e)
        {
            //spielstatus in static-klasse speichern
            saveGame();

            //aktuelle Seite merken
            lastPage = MainPivot.SelectedIndex;

            //auf Extra-seite für anleitung springen
            string parameter = MainPivot.SelectedIndex.ToString();
            NavigationService.Navigate(new Uri(string.Format("/PageAbout.xaml?parameter={0}", parameter), UriKind.Relative));
        }

    }//end class
}//end namespace