﻿#pragma checksum "C:\Users\Erik\documents\visual studio 2010\Projects\Feuerkelch_v0.3\Feuerkelch_v0.3\MainPage.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "214A2DD2D20FD703AD5F9C52FD08CD38"
//------------------------------------------------------------------------------
// <auto-generated>
//     Dieser Code wurde von einem Tool generiert.
//     Laufzeitversion:4.0.30319.261
//
//     Änderungen an dieser Datei können falsches Verhalten verursachen und gehen verloren, wenn
//     der Code erneut generiert wird.
// </auto-generated>
//------------------------------------------------------------------------------

using Microsoft.Phone.Controls;
using System;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Automation.Peers;
using System.Windows.Automation.Provider;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Interop;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Resources;
using System.Windows.Shapes;
using System.Windows.Threading;


namespace Feuerkelch_v0._3 {
    
    
    public partial class MainPage : Microsoft.Phone.Controls.PhoneApplicationPage {
        
        internal System.Windows.Controls.Grid LayoutRoot;
        
        internal Microsoft.Phone.Controls.Pivot MainPivot;
        
        internal Microsoft.Phone.Controls.PivotItem welcome;
        
        internal System.Windows.Controls.TextBlock LabelDrinkResponsibly;
        
        internal System.Windows.Controls.Image image1;
        
        internal System.Windows.Controls.Image image2;
        
        internal System.Windows.Controls.Image image3;
        
        internal System.Windows.Controls.Image image4;
        
        internal Microsoft.Phone.Controls.PivotItem settings;
        
        internal System.Windows.Controls.TextBlock labelName;
        
        internal System.Windows.Controls.TextBox textBoxSpielerName;
        
        internal System.Windows.Controls.Image imageAddPlayer;
        
        internal System.Windows.Controls.ListBox PlayerList;
        
        internal System.Windows.Controls.Image imageDeletePlayer;
        
        internal System.Windows.Controls.TextBlock textBlockSpielerListe;
        
        internal System.Windows.Controls.HyperlinkButton ButtonPlay;
        
        internal System.Windows.Controls.TextBlock LabelMaxPlayers;
        
        internal Microsoft.Phone.Controls.PivotItem game;
        
        internal System.Windows.Controls.TextBlock LabelCurrentPlayer;
        
        internal System.Windows.Controls.TextBlock textBlockCurrentPlayer;
        
        internal System.Windows.Controls.Image ImageCurrentCard;
        
        internal System.Windows.Controls.TextBlock LabelAnleitung;
        
        internal System.Windows.Controls.TextBlock manualContent;
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Windows.Application.LoadComponent(this, new System.Uri("/Feuerkelch_v0.3;component/MainPage.xaml", System.UriKind.Relative));
            this.LayoutRoot = ((System.Windows.Controls.Grid)(this.FindName("LayoutRoot")));
            this.MainPivot = ((Microsoft.Phone.Controls.Pivot)(this.FindName("MainPivot")));
            this.welcome = ((Microsoft.Phone.Controls.PivotItem)(this.FindName("welcome")));
            this.LabelDrinkResponsibly = ((System.Windows.Controls.TextBlock)(this.FindName("LabelDrinkResponsibly")));
            this.image1 = ((System.Windows.Controls.Image)(this.FindName("image1")));
            this.image2 = ((System.Windows.Controls.Image)(this.FindName("image2")));
            this.image3 = ((System.Windows.Controls.Image)(this.FindName("image3")));
            this.image4 = ((System.Windows.Controls.Image)(this.FindName("image4")));
            this.settings = ((Microsoft.Phone.Controls.PivotItem)(this.FindName("settings")));
            this.labelName = ((System.Windows.Controls.TextBlock)(this.FindName("labelName")));
            this.textBoxSpielerName = ((System.Windows.Controls.TextBox)(this.FindName("textBoxSpielerName")));
            this.imageAddPlayer = ((System.Windows.Controls.Image)(this.FindName("imageAddPlayer")));
            this.PlayerList = ((System.Windows.Controls.ListBox)(this.FindName("PlayerList")));
            this.imageDeletePlayer = ((System.Windows.Controls.Image)(this.FindName("imageDeletePlayer")));
            this.textBlockSpielerListe = ((System.Windows.Controls.TextBlock)(this.FindName("textBlockSpielerListe")));
            this.ButtonPlay = ((System.Windows.Controls.HyperlinkButton)(this.FindName("ButtonPlay")));
            this.LabelMaxPlayers = ((System.Windows.Controls.TextBlock)(this.FindName("LabelMaxPlayers")));
            this.game = ((Microsoft.Phone.Controls.PivotItem)(this.FindName("game")));
            this.LabelCurrentPlayer = ((System.Windows.Controls.TextBlock)(this.FindName("LabelCurrentPlayer")));
            this.textBlockCurrentPlayer = ((System.Windows.Controls.TextBlock)(this.FindName("textBlockCurrentPlayer")));
            this.ImageCurrentCard = ((System.Windows.Controls.Image)(this.FindName("ImageCurrentCard")));
            this.LabelAnleitung = ((System.Windows.Controls.TextBlock)(this.FindName("LabelAnleitung")));
            this.manualContent = ((System.Windows.Controls.TextBlock)(this.FindName("manualContent")));
        }
    }
}

