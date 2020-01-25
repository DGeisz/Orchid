package com.exfizzassist.orchid.view;

import com.exfizzassist.orchid.MainApp;

public class RootLayoutController {

    /** Reference to the main application*/
    private MainApp mainApp;

    /**
     * Called by the main application to give a reference
     * back to itself
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
