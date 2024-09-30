public class monkey_banana {
    public monkey_banana() {
    }
    public static void main(String[] args) {
        
        State state = new State(true, false);
        System.out.println("Current position: " + state);

        
        do {
                                        
            if (state.monkeyOnGround) {
                System.out.println("Action: Move monkey to the box");
                state.monkeyOnGround = false;
            }

            System.out.println("Current position: " + state);

            
            if (!state.monkeyOnGround && !state.monkeyHasBanana) {
                System.out.println("Action: Monkey grabs the banana");
                state.monkeyHasBanana = true;
            }

            System.out.println("Current position: " + state);

            
            if (!state.monkeyOnGround && state.monkeyHasBanana) {
                System.out.println("Action: Monkey comes down with the banana");
                state.monkeyOnGround = true;
            }

            System.out.println("Current position: " + state);
        } while (!state.monkeyOnGround || !state.monkeyHasBanana);

        
        System.out.println("Goal reached: " + state);
    }
}

class State {
    boolean monkeyOnGround;
    boolean monkeyHasBanana;

    public State(boolean monkeyOnGround, boolean monkeyHasBanana) {
        this.monkeyOnGround = monkeyOnGround;
        this.monkeyHasBanana = monkeyHasBanana;
    }

    @Override
    public String toString() {
        return "Monkey on ground: " + monkeyOnGround + ", Monkey has banana: " + monkeyHasBanana;
    }
}
