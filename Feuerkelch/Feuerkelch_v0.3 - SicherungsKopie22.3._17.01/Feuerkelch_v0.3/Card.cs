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

        public Card(int nummer, String farbe)
        {
            setNr(nummer);
            setSource(farbe);
            //@todo manual aus rules.xml auslesen
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

        public String getManual()
        {
            return manual;
        }
    }
}
