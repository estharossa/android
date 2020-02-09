package com.example.calculator;

public class Calculator {

    private double first;
    private double second;
    private State state;
    private StringBuilder inputStr = new StringBuilder();
    private int actionSelected;

    public Calculator(){
        state = State.firstArgInput;
    }

    private enum State{
        firstArgInput,
        secondArgInput,
        resultShow
    }

    public void onNumPressed(int buttonId){
        if (state == State.resultShow){
            state = State.firstArgInput;
            inputStr.setLength(0);
        }

        switch (buttonId){
            case R.id.zero:
                if(inputStr.length() != 0){
                    inputStr.append("0");
                }
                break;
            case R.id.one:
                inputStr.append("1");
                break;
            case R.id.two:
                inputStr.append("2");
                break;
            case R.id.three:
                inputStr.append("3");
                break;
            case R.id.four:
                inputStr.append("4");
                break;
            case R.id.five:
                inputStr.append("5");
                break;
            case R.id.six:
                inputStr.append("6");
                break;
            case R.id.seven:
                inputStr.append("7");
                break;
            case R.id.eight:
                inputStr.append("8");
                break;
            case R.id.nine:
                inputStr.append("9");
                break;
            case R.id.comma:
                inputStr.append(".");
            case R.id.clear:
                inputStr.setLength(0);
        }
    }

    public void clearBox(int clearId){
        inputStr.setLength(0);
        state = State.firstArgInput;
    }

    public void onActionPressed(int actionId){
        if (actionId == R.id.equals && state == State.secondArgInput){
            second = Double.parseDouble(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);
            switch (actionSelected){
                case R.id.plus:
                    inputStr.append(first + second);
                    break;
                case R.id.minus:
                    inputStr.append(first - second);
                    break;
                case R.id.multiply:
                    inputStr.append(first * second);
                    break;
                case R.id.division:
                    inputStr.append(first / second);
                    break;

            }
        }
        else if(inputStr.length() > 0 && state == State.firstArgInput){
            first = Double.parseDouble(inputStr.toString());
            state = State.secondArgInput;
            inputStr.setLength(0);
            switch (actionId){
                case R.id.plus:
                    actionSelected = R.id.plus;
                    break;
                case R.id.minus:
                    actionSelected = R.id.minus;
                    break;
                case R.id.multiply:
                    actionSelected = R.id.multiply;
                    break;
                case R.id.division:
                    actionSelected = R.id.division;
                    break;
            }
        }
    }

    public String getText(){
        return inputStr.toString();
    }
}
