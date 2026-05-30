//package main;
//import db.DBConnection;
//
//public class Main {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		DBConnection.getConnection();
//	}
//}

package main;

import ui.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ensure UI updates happen on the Event Dispatch Thread (standard Swing practice)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // This will open the login frame when your teammates finish coding it
                new LoginFrame().setVisible(true);
            }
        });
    }
}