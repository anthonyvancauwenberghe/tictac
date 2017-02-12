import javax.swing.*;

/**
 * @author Daniel
 */
public class Dialog {

    public static boolean acceptedChoice(JFrame instance, final String title, final String message) {
        return JOptionPane.showConfirmDialog(instance, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
    }

    public static boolean acceptedChoice(JApplet instance, final String title, final String message) {
        return JOptionPane.showConfirmDialog(instance, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
    }

    public static void displayMessage() {
        JOptionPane.showMessageDialog(null, "The Process will now Exit.");
    }

    public static boolean acceptedChoice(final String title, final String message) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
    }

}
