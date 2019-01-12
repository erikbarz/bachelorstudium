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

namespace Feuerkelch_v0._3
{
    public partial class MainPage : PhoneApplicationPage
    {
        String[] players;
        int currentPlayerPosition=0;
        String currentPlayer="";
        Card currentCard;
        int currentRound = 1;
        List<Card> karten;

        
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

        private void ButtonPlay_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            //array mit spielern füllen
            players = new String[PlayerList.Items.Count];
            for (int i = 0; i < PlayerList.Items.Count; i++ )
            {
                players[i] = (String)PlayerList.Items.ElementAt(i);
            }

            //ersten spieler setzen
            currentPlayerPosition = 0;
            currentPlayer=players[currentPlayerPosition];

            //liste karten füllen
            for (int a = 7; a < 15; a++)
            {
                for (int b = 0; b < 4;b++ )
                {
                    String farbe="farbe";
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


        //methoden für pivotItem SPIEL

        private void buttonNextCard_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            //nächste Karte ziehen
            currentCard = setNextCard();
            BitmapImage nextSource = new BitmapImage(new Uri(currentCard.getSource(), UriKind.Relative)); 
            ImageCurrentCard.Source = nextSource; 

            //nächsten Spieler anzeigen
            setNextPlayer();
            textBlockCurrentPlayer.Text = currentPlayer;

            //Hilfe zu Karte anzeigen

        }

        private void setNextPlayer()
        {
            if (currentPlayerPosition >= players.Count())
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
            int i = zufallszahl.Next(7,32-currentRound);
            Card karte=karten.ElementAt(i);
            karten.RemoveAt(i);
            return karte;
        }

        
    }

}