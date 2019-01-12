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
        int kings = 0;

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
        
    }//end class
}//end namespace