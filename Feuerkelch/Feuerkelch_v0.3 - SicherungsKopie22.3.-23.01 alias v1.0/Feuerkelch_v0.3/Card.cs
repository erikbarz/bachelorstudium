using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Feuerkelch_v0._3
{
    class Card
    {
        String source="/Images/";
        int nr = 7;
        String manual = "";

        //normaler Konstruktor
        public Card(int nummer, String farbe)
        {
            setNr(nummer);
            setSource(farbe);
            setManual();
        }

        //Konstruktor für backsideCard
        public Card()
        {
            setNr(1);
            source = "/Feuerkelch_v0.3;component/Images/aa_backside.png";
            manual="Klick auf die umgedrehte Karte um sie aufzudecken!";
        }

        public void setSource(String farbe)
        {
            source = source + nr + "-" + farbe + ".png";
        }

        public String getSource()
        {
            return source;
        }

        public void setNr(int nummer)
        {
            nr = nummer;
        }

        public int getNr()
        {
            return nr;
        }

        public void setManual(String m)
        {
            manual = m;
        }

        public void setManual()
        { 
            switch (nr)
            {
                case 7: manual = "Dein linker Nachbar muss trinken!";
                        break;
                case 8: manual = "Dein rechter Nachbar muss trinken!";
                        break;
                case 9: manual = "Suche dir einen Mitspieler aus, der trinken muss!";
                        break;
                case 10: manual = "Schluck du Luder!";
                        break;
                case 11: manual = "Stelle eine beliebige Regel auf. Die Regel gilt bis zum Ende des Spiels. Wer dagegen verstößt, muss trinken! (z.B. \"Es darf nur mit der linken Hand getrunken werden\")";
                        break;
                case 12: manual = "Du bist nun Questionmaster! Wer dir eine Frage beantwortet, muss seine Antwort mit \"Mister Questionmaster\" beginnen, sonst muss er trinken.";
                        break;
                case 13: manual = "Fülle ein beliebiges Getränk in den Krug in der Mitte!";
                        break;
                case 14: manual = "Wasserfall! Alle müssen trinken.";
                        break;
            }
        }

        public String getManual()
        {
            return manual;
        }
    }
}
