package fa.nfa;

import fa.State;
import java.util.*;

public class NFAState extends State
{
    private boolean isStart, isFinal;
    private NFAState previousState;
    
    public NFAState(String name)
    {
        //TODO
    }

    public NFAState(String name, boolean isStart, boolean isFinal)
    {
        //TODO
    }

    public void setStartState(boolean value)
    {
        isStart = value;
    }
    
    public boolean isStartState()
    {
        return isStart;
    }

    public void setFinalState(boolean value)
    {
        isFinal = value;
    }
    
    public boolean isFinalState()
    {
        return isFinal;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setPreviousState(NFAState oldState)
    {
        //TODO
    }

    public NFAState getPreviousState()
    {
        return previousState;
    }
    
    public Set<NFAState> getToState(char symb)
    {
        //TODO
        return null;
    }
    
    private void initDefault(String name)
    {
        //TODO
    }

    public Set<NFAState> getEStates()
    {
        //TODO
        return null;
    }

    public void addTransition(char onSymb, NFAState toState)
    {
        //TODO
    }
}
