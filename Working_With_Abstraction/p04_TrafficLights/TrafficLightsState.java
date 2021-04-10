package TrafficLights;

public class TrafficLightsState {
    private TrafficLights state;

    TrafficLightsState(TrafficLights state){
        this.state = state;
    }

    public void update(){
        switch (state){
            case RED:
               this.state = TrafficLights.GREEN;
                break;
            case GREEN:
                this.state = TrafficLights.YELLOW;
                break;
            case YELLOW:
                this.state = TrafficLights.RED;
                break;
        }
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}
