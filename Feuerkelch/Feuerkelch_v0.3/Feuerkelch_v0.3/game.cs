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
        private String[] players;
        private int currentPlayerPosition = 0;
        private String currentPlayer = "";

        private Card currentCard;
        private List<Card> karten = new List<Card>();

        private int currentRound;
        private bool isWon = false;
        private bool isStarted = false;
        private int kings;

        //speichern
        public void setGame(String[] players2, int currentPlayerPosition2, String currentPlayer2, Card currentCard2, List<Card> karten2, int currentRound2, bool isWon2, bool isStarted2, int kings2)
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

        public Game(String[] players2, int currentPlayerPosition2, String currentPlayer2, Card currentCard2, List<Card> karten2, int currentRound2, bool isWon2, bool isStarted2, int kings2)
        {
            //aufruf von setGame
            setGame(players2, currentPlayerPosition2, currentPlayer2, currentCard2, karten2, currentRound2, isWon2, isStarted2, kings2);
        }

        public Game()
        {
            //nichts
        }

        //alle getter zum spiel-laden:

        public String[] getPlayers()
        {
            return players;
        }

        public int getCurrentPlayerPosition()
        {
            return currentPlayerPosition;
        }

        public String getCurrentPlayer()
        {
            return currentPlayer;
        }

        public Card getCurrentCard()
        {
            return currentCard;
        }

        public List<Card> getKarten()
        {
            return karten;
        }

        public int getCurrentRound()
        {
            return currentRound;
        }

        public bool getIsWon()
        {
            return isWon;
        }

        public bool getIsStarted()
        {
            return isStarted;
        }

        public int getKings()
        {
            return kings;
        }

        //für saveToXmlFile
        //public System.Xml.Linq.XStreamingElement ToString { get; set; }

        /* nicht benötigt
         
        public String toString()
        {
            String ausgabe = "";

            //falls nichts zu speichern ist
            if (getIsWon() == false && getIsStarted() == false)
            {
                return "no game";
            }
            else
            {
                //ausgabe mit allem außer players und karten füllen
                ausgabe = "currentCard=" + getCurrentCard() + ";currentPlayer=" + getCurrentPlayer() + ";currentPlayerPosition=" + getCurrentPlayerPosition() +
                ";currentRound=" + getCurrentRound() + ";isStarted=" + getIsStarted() + ";isWon=" + getIsWon() + ";kings=" + getKings() + ";players:";

                //players auflösen
                for (int i = 0; i < players.Length; i++)
                {
                    ausgabe = ausgabe + "player" + i + players[i] + "--";
                }

                //karten auflösen
                ausgabe = ausgabe + ";karten:";
                foreach (Card karte in karten)
                {

                }

                return ausgabe;
            }
        }
         */


    }
}
