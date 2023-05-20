package alekseev.pet.labs.Lab6.Runnables;


import alekseev.pet.labs.Lab6.State.STATE;

public class Program implements Runnable {
    private volatile STATE state;

    public Program(STATE state) {
        this.state = state;
    }

    @Override
    public void run() {
        try {
            while(state == STATE.RUNNING) {
                Thread.sleep(1000);
                System.out.println("Program state: " + state);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized STATE getState() {
        return state;
    }

    public synchronized void setState(STATE state) {
        this.state = state;
    }

}
