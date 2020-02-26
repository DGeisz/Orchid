package com.exfizzassist.orchid.testing;

import com.exfizzassist.orchid.MainApp;
import com.exfizzassist.orchid.model.editor_model.Dock;
import com.exfizzassist.orchid.model.editor_model.EditorComplex;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Test1 {

    static class EditorThread extends Thread {
        public void run() {
            try {
                String[] args = {};
                MainApp.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    static class TestingThread extends Thread {
        private void typeString(String seq, Robot robot) {
            for (int i = 0; i < seq.length(); i++) {
//                robot.keyPress(Character.getNumericValue(seq.charAt(i)));
                robot.keyPress(Character.toUpperCase(seq.charAt(i)));
            }
        }

        public void run() {
            try {
                try {
                    Robot r = new Robot();
                    typeString("set", r);
                    r.keyPress(KeyEvent.VK_ENTER);
                    typeString("a", r);
                    Thread.sleep(100);
                    r.keyPress(KeyEvent.VK_ENTER);
                    r.keyPress(KeyEvent.VK_ENTER);
                    r.keyPress(KeyEvent.VK_ENTER);
                    typeString("mapdef", r);
                    r.keyPress(KeyEvent.VK_ENTER);
                    typeString("r", r);
                    r.keyPress(KeyEvent.VK_ENTER);
                    typeString("a", r);
                    r.keyPress(KeyEvent.VK_ENTER);
                    typeString("a", r);
                    r.keyPress(KeyEvent.VK_ENTER);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        EditorThread editorThread = new EditorThread();
        editorThread.start();
        Thread.sleep(3000);
        TestingThread testingThread = new TestingThread();
        testingThread.start();

    }
}
