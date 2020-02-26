package com.exfizzassist.orchid.testing;

import com.exfizzassist.orchid.MainApp;
import com.exfizzassist.orchid.model.editor_model.Dock;
import com.exfizzassist.orchid.model.editor_model.EditorComplex;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class IntegrationTester {

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

        /**
         * Delay between new lines
         */
        int delay = 100;

        /**
         * Name of the test
         */
        String filename;

        TestingThread(String filename) {
            this.filename = filename;
        }

        private void typeString(String s, Robot robot) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isUpperCase(c)) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                }
                robot.keyPress(Character.toUpperCase(c));
                robot.keyRelease(Character.toUpperCase(c));

                if (Character.isUpperCase(c)) {
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
            }
            robot.delay(delay);
        }

        private void inputLine(String s, Robot r) {
            String[] allStrings = s.split("\\s");
            for (String str : allStrings) {
                typeString(str, r);
                r.keyPress(KeyEvent.VK_ENTER);
            }
        }

        public void run() {
            try {
                if (!filename.endsWith(".txt")) {
                    filename += ".txt";
                }
                String workingDir = System.getProperty("user.dir");
                File file = new File(workingDir + "\\src\\com\\exfizzassist\\orchid\\testing\\tests\\" + filename);

                BufferedReader br = new BufferedReader(new FileReader(file));


                try {
                    Robot r = new Robot();
                    String currLine;
                    while ((currLine = br.readLine()) != null) {
                        inputLine(currLine, r);
                        r.keyPress(KeyEvent.VK_ENTER);
                    }
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {


        if (args.length != 1) {
            System.out.println("Expected one argument");
            return;
        }
        EditorThread editorThread = new EditorThread();
        editorThread.start();
        Thread.sleep(1000);
        TestingThread testingThread = new TestingThread(args[0]);
        testingThread.start();

    }
}
