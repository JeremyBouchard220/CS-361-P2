package fa.nfa;

import fa.State;
import java.util.*;

public class NFAState extends State
{
    private boolean isStart, isFinal;
    private NFAState previousState;
    private HashMap<Character, LinkedHashSet<NFAState>> delta;
    
    public NFAState(String name)
    {
        initDefault(name);
        isFinal = false;
        isStart = false;
    }

    public NFAState(String name, boolean isStart, boolean isFinal)
    {
        initDefault(name);
        this.isFinal = isFinal;
        this.isStart = isStart;
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
        previousState = oldState;
    }

    public NFAState getPreviousState()
    {
        return previousState;
    }
    
    public Set<NFAState> getToState(char symb)
    {
        LinkedHashSet<NFAState> ret = delta.get(symb);
        return ret;
    }
    
    private void initDefault(String name)
    {
        this.name = name;
        delta = new HashMap<Character, LinkedHashSet<NFAState>>();
    }

    public Set<NFAState> getEStates()
    {
        Set returnSet = new LinkedHashSet<>();
        
        if(delta.containsKey('e'))
        {
            returnSet = delta.get('e');
        }

        return returnSet;
    }

    public void addTransition(char onSymb, NFAState toState)
    {
        LinkedHashSet<NFAState> newSet = new LinkedHashSet<>();
        
        if(!delta.containsKey(onSymb))
        {
            newSet.add(toState);
            delta.put(onSymb, newSet);
        }
        
        else
        {
            delta.get(onSymb).add(toState);
        }
    }
}
