package fa.nfa;

import fa.State;
import java.util.*;

/**
 * @author Sam Jackson and Jeremy Bouchard
 * Models a state of a Non-Deterministic Finite
 * Autonoma
 */
public class NFAState extends State
{
    private boolean isStart, isFinal;
    private NFAState previousState;
    private HashMap<Character, LinkedHashSet<NFAState>> delta;
    
    /**
     * Creates a non final, non start NFA state
     * @param name of the state
     */
    public NFAState(String name)
    {
        initDefault(name);
        isFinal = false;
        isStart = false;
    }

    /**
     * Creates an NFA that may be a start
     * or final state (or both)
     * @param name
     * @param isStart
     * @param isFinal
     */
    public NFAState(String name, boolean isStart, boolean isFinal)
    {
        initDefault(name);
        this.isFinal = isFinal;
        this.isStart = isStart;
    }

    /**
     * @param value if true, sets this state to start state
     */
    public void setStartState(boolean value)
    {
        isStart = value;
    }
    
    /**
     * @return true if this is a start state
     */
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
