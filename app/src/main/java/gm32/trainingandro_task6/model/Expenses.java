package gm32.trainingandro_task6.model;

public class Expenses {
    private String label;
    private Double become;

    public Expenses(String label, Double become){
        this.label= label;
        this.become= become;
    }

    public String getLabel(){
        return label;
    }

    public Double getBecome() {
        return become;
    }

    public void setLabel(String label){
        this.label= label;
    }

    public void setBecome(Double become){
        this.become= become;
    }

}
