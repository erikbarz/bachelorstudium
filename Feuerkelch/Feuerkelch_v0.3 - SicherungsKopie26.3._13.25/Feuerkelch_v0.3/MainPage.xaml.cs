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
        String[] players;
        int currentPlayerPosition=0;
        String currentPlayer="";

        Card backsideCard = new Card();
        Card currentCard;
        List<Card> karten= new List<Card>();

        int currentRound;
        bool isWon = false;
        bool isStarted = false;
        int kings;

        Game gameDAO;
        int lastPage=0;

        // Konstruktor
        public MainPage()
        {
            InitializeComponent();

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
                textBoxSpielerName.Text = "Spielername";
            }
        }

        private void textBoxSpielerName_GotFocus(object sender, RoutedEventArgs e)
        {
            textBoxSpielerName.Text = "";
        }

        private void imageDeletePlayer_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            PlayerList.Items.Remove(PlayerList.SelectedItem);
            //StartButton deaktivieren falls zu wenige Spieler
            if (PlayerList.Items.Count < 2)
            {
                ButtonPlay.IsEnabled = false;
            }
        }

        private void ButtonPlay_Click(object sender, RoutedEventArgs e)
        {
            //neues Spiel initialisieren
            karten.Clear();
            isWon = false;
            kings = 0;
            currentRound = 1;

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

            isStarted = true;

            //startbutton deaktivieren
            ButtonPlay.IsEnabled = false;

            //@todo: wechsel zur spielseite
            MainPivot.SelectedItem = game;

        }


        //methoden für pivotItem SPIEL
 
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

        private Card setNextCard()
        {
            Random zufallszahl = new Random();
            int i = zufallszahl.Next(1, 33 - currentRound);
            Card karte=karten.ElementAt(i);
            karten.RemoveAt(i);
            return karte;
        }

        private void ImageCurrentCard_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            if (!isWon&&isStarted)
            {
                if (currentCard == backsideCard)
                {
                    //nächste Karte ziehen
                    currentCard = setNextCard();
                    BitmapImage nextSource = new BitmapImage(new Uri("/Feuerkelch_v0.3;component" + currentCard.getSource(), UriKind.Relative));
                    ImageCurrentCard.Source = nextSource;
                    
                    //@todo: hilfetext aus datei lesen
                    //neuen Hilfetext einblenden
                    manualContent.Text = currentCard.getManual();
                
                    //Rundenzahl inkrementieren
                    currentRound++;

                    //auf König und gewonnen prüfen
                    if (currentCard.getNr() == 13)
                    {
                        kings++;
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
        }

        private void ApplicationBarIconButton_Click(object sender, EventArgs e)
        {
            //@todo: spielstatus in datei schreiben
            saveGame();


            lastPage = MainPivot.SelectedIndex;

            //@todo: auf Extra-seite für anleitung springen
            string parameter = MainPivot.SelectedIndex.ToString();
            NavigationService.Navigate(new Uri(string.Format("/PageManual.xaml?parameter={0}", parameter), UriKind.Relative));

        }

        public void saveGame()
        {
            Game.setGame(players, currentPlayerPosition, currentPlayer, currentCard, karten, currentRound, isWon, isStarted, kings);

            //PhoneApplicationService.Current.State["game"] = gameDAO;

            /*
            XDocument doc = new XDocument(new XElement(gameDAO.ToString));
            using (IsolatedStorageFile isoStore = IsolatedStorageFile.GetUserStoreForApplication())
            {
                using (IsolatedStorageFileStream isoStream =
                    new IsolatedStorageFileStream("gameState.xml", FileMode.Create, isoStore))
                {
                    doc.Save(isoStream);
                }
            }
             * */
        }

        public void getGame()
        {
            /*
            using (IsolatedStorageFile isoStore = IsolatedStorageFile.GetUserStoreForApplication())
            {
                using (IsolatedStorageFileStream isoStream = new IsolatedStorageFileStream("gameState.xml", FileMode.Open, isoStore))
                {
                    XDocument doc1 = XDocument.Load(isoStream);
                    //OutputTextBlock.Text = doc1.ToString();

                    //@todo: stream aus datei in string umwandeln un auswerten
                }
            }
             * */

                //gameDAO = Game(PhoneApplicationService.Current.State["game"]);

                //werte holen
                this.players = Game.getPlayers();
                this.currentPlayerPosition = Game.getCurrentPlayerPosition();
                this.currentPlayer = Game.getCurrentPlayer();
                this.currentCard = Game.getCurrentCard();
                this.karten = Game.getKarten();
                this.currentRound = Game.getCurrentRound();
                this.isWon = Game.getIsWon();
                this.isStarted = Game.getIsStarted();
                this.kings = Game.getKings();
                
                //anzeige setzen
                textBlockCurrentPlayer.Text = currentPlayer;
                for (int z = 0; z < players.Count(); z++)
                {
                    PlayerList.Items.Add(players[z]);
                }
                //neuen Hilfetext einblenden
                manualContent.Text = currentCard.getManual();
                //umgedrehte Karte ziehen
                String source = "/Feuerkelch_v0.3;component"+currentCard.getSource();
                BitmapImage nextSource = new BitmapImage(new Uri(source, UriKind.Relative));
                ImageCurrentCard.Source = nextSource;

                
        }

        public static PivotItem SelectedItem { get; set; }


        //überschriebene funktion für navigation
        //stellt den ursprünglichen Spielstand wiederher
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);
            try
            {
                string newparameter = this.NavigationContext.QueryString["parameter"];

                lastPage = int.Parse(newparameter);

                MainPivot.SelectedIndex = lastPage;
                
                /*
                if (newparameter.Equals("game"))
                {
                    MainPivot.SelectedItem = game;
                }
                else
                {
                    MainPivot.SelectedItem = welcome;
                }
                 * */
                getGame();
            }
            catch (Exception exec)
            {
                MainPivot.SelectedItem = welcome;
            }
                

            //@todo: spielstatus aus datei auslesen
            //getGame();
        }

    }//end class
}//end namespace