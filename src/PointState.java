public class PointState extends State {
    // ------------------------------------------------------------------------
// Second roll of dice:
//    7 Loss ("seven out")
//    match of previous roll Win ("hits the points")
//    any other, roll again
// ------------------------------------------------------------------------
    private int points_to_match;


    public PointState(State source) {
        super(source);
        points_to_match = source.getContext().getCurrentRoll();
    }

    public PointState(Dice dice) {
        super(dice);
        System.out.println("** Invalid State Transition Exception **");
        System.exit(0);
    }

    public void transitionState() {
        if(getContext().getCurrentRoll() == 7) {
            System.out.println("(Lose)");
            getContext().setState(new Loss(this));
        }
        else if(getContext().getCurrentRoll() == points_to_match) {
            System.out.println("(Win)");
            getContext().setState(new Win(this));
        }
        else{
            System.out.println("(Roll Again)");
            getContext().rollDice();
        }
    }
    //  transitions to either a Win or Loss state
}
