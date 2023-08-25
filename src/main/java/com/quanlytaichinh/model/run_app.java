package com.quanlytaichinh.model;

import com.quanlytaichinh.view.HomeViewPro;
import com.quanlytaichinh.view.LoginView;
import javax.swing.SwingUtilities;

public class run_app {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
}