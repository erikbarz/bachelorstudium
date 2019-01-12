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
using System.Windows.Navigation;

namespace Feuerkelch_v0._3
{
    public partial class PageManual : PhoneApplicationPage
    {
        //letzte seite im pivot-teil
        String lastPage = "";

        public PageManual()
        {
            InitializeComponent();
        }

        //auf zuletzt aufgerufenen pivot-teil zurückspringen
        private void ApplicationBarIconButton_Click(object sender, EventArgs e)
        {
            string parameter = lastPage;
            NavigationService.Navigate(new Uri(string.Format("/MainPage.xaml?parameter={0}", parameter), UriKind.Relative));
        }

        //wenn seite aufgerufen wird, letzte seite speichern
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            string newparameter = this.NavigationContext.QueryString["parameter"];
            lastPage = newparameter;
        }

    }//end class
}//end namespace