package alekseev.pet.labs.Lab6.Runnables;


import alekseev.pet.labs.Lab6.State.STATE;

public class RandomizerDaemon implements Runnable {
    private final Program program;
    private boolean mainThreadStopped;

    public RandomizerDaemon(Program program) {
        this.program = program;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                program.setState(STATE.randomState());
                if(program.getState() == STATE.FATAL_ERROR) {
                    break;
                }
            }
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public synchronized void setMainThreadStopped(boolean mainThreadStopped) {
        this.mainThreadStopped = mainThreadStopped;
    }
}
