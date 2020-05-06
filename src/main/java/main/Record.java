package main;

public class Record {

    String cards;
    String position;
    String vs;
    String action;
    int percentage;
    String result;

    public Record(String cards, String position, String vs, String action, int percentage, String result) {
        this.cards = cards;
        this.position = position;
        this.vs = vs;
        this.action = action;
        this.percentage = percentage;
        this.result = result;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public String getHand() {
        return cards;
    }

    public void setHand(String hand) {
        this.cards = hand;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVs() {
        return vs;
    }

    public void setVs(String vs) {
        this.vs = vs;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Record{" + "cards=" + cards + ", position=" + position + ", vs=" + vs + ", action=" + action + ", percentage=" + percentage + ", result=" + result + '}';
    }

}
