using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.Collections.Generic;
using System.Runtime.Serialization; 

namespace Feuerkelch_v0._3
{
    //static-Klasse für den Spiel-status
    public class Game
    {
        private static String[] players;
        private static int currentPlayerPosition = 0;
        private static String currentPlayer = "";

        private static Card currentCard;
        private static List<Card> karten = new List<Card>();

        private static int currentRound;
        private static bool isWon = false;
        private static bool isStarted = false;
        private static int kings;

        //speichern
        public static void setGame(String[] players2, int currentPlayerPosition2, String currentPlayer2, Card currentCard2, List<Card> karten2, int currentRound2, bool isWon2, bool isStarted2, int kings2) 
        {
            players = players2;
            currentPlayerPosition = currentPlayerPosition2;
            currentPlayer = currentPlayer2;
            currentCard = currentCard2;
            karten = karten2;
            currentRound = currentRound2;
            isWon = isWon2;
            isStarted = isStarted2;
            kings = kings2;
        }

        public Game()
        {
            //leer
        }

        //alle getter zum spiel-laden:

        public static String[] getPlayers()
        {
            return players;
        }

        public static int getCurrentPlayerPosition()
        {
            return currentPlayerPosition;
        }

        public static String getCurrentPlayer()
        {
            return currentPlayer;
        }

        public static Card getCurrentCard()
        {
            return currentCard;
        }

        public static List<Card> getKarten()
        {
            return karten;
        }

        public static int getCurrentRound()
        {
            return currentRound;
        }

        public static bool getIsWon()
        {
            return isWon;
        }

        public static bool getIsStarted()
        {
            return isStarted;
        }

        public static int getKings()
        {
            return kings;
        }

        //für saveToXmlFile
        //public System.Xml.Linq.XStreamingElement ToString { get; set; }
    }
}
