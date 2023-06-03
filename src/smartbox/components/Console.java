package smartbox.components;

import java.io.*;

import smartbox.*;

public class Console extends Component implements App {

    public CommandProcessor processor;
    transient protected BufferedReader stdin;
    transient protected PrintWriter stdout;
    transient protected PrintWriter stderr;

    public Console(CommandProcessor processor) {
        this.processor = processor;
        stdout = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(System.out)), true);
        stderr = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(System.err)), true);
        stdin = new BufferedReader(
                new InputStreamReader(System.in));
    }

    public Console() {
        stdout = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(System.out)), true);
        stderr = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(System.err)), true);
        stdin = new BufferedReader(
                new InputStreamReader(System.in));
    }

    public void repl() {
        if(stdout == null || stderr == null || stdin == null) {
            stdout = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(System.out)), true);
            stderr = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(System.err)), true);
            stdin = new BufferedReader(
                    new InputStreamReader(System.in));
        }
        while (true) {
            try {
                stdout.print("-> ");
                stdout.flush();
                String request = stdin.readLine();
                if (request == null) continue;
                if (request.equals("quit")) break;
                String response = processor.execute(request);
                stdout.println("result: " + response);
            } catch (Exception e) {
                stderr.println(e.getMessage());
            }
        }
        stdout.println("bye");
    }

    public void main() throws Exception {
        repl();
    }
}