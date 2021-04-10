package fa.nfa;

import java.util.*;
import fa.State;
import fa.dfa.DFA;
import fa.dfa.DFAState;

public class NFA implements NFAInterface
{
    private LinkedHashSet<NFAState> Q;
    private HashSet<Character> alphabet;
    private ArrayList<NFAState> visit;
    private LinkedHashSet<String> originalTransitions;
    private Set<String> listFinalStates = new HashSet<String>();

    public NFA()
    {
        Q = new LinkedHashSet<NFAState>();
        alphabet = new HashSet<Character>();
        visit = new ArrayList<NFAState>();
        originalTransitions = new LinkedHashSet<String>();
    }
    
    @Override
    public void addStartState(String name) 
    {
        NFAState newStartState = new NFAState(name);
        newStartState.setStartState(true);

        @SuppressWarnings("unchecked")
        Set<NFAState> finalStates = (Set<NFAState>) getFinalStates();
        boolean finalAndStart = false;
        
        for(NFAState fState : finalStates)
        {
            if (fState.getName().equals(name))
            {
                finalAndStart = true;
                fState.setStartState(true);
            }
        }

        if(!(Q.add(newStartState)))
        {
            if(!finalAndStart)
            {
                Iterator<NFAState> it = Q.iterator();
                while(it.hasNext())
                {
                    newStartState = it.next();
                    if(newStartState.getName().equals(name))
                    {
                        newStartState.setStartState(true);
                    }
                }
            }
        }
    }

    @Override
    public void addState(String name) 
    {
        NFAState newState = new NFAState(name);
        Q.add(newState);
    }

    @Override
    public void addFinalState(String name) 
    {
        NFAState newFinalState = new NFAState(name);
        newFinalState.setFinalState(true);
        Q.add(newFinalState);
        listFinalStates.add(name);
    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) 
    {
        if(onSymb != 'e')
        {
            alphabet.add(onSymb);
        }

        originalTransitions.add(fromState + onSymb + toState);
        Iterator<NFAState> it = Q.iterator();

        while(it.hasNext())
        {
            NFAState tempState = it.next();
            
            if(tempState.getName().equals(fromState))
            {
                NFAState tempState2 = null;
                
                for(NFAState temp2 : Q)
                {
                    
                    if(temp2.getName().equals(toState))
                    {
                        tempState2 = temp2;
                        break;
                    }
                }

                tempState.addTransition(onSymb, tempState2);
                break;
            }
        }
    }

    @Override
    public Set<? extends State> getStates() 
    {
        LinkedHashSet<NFAState> states = new LinkedHashSet<NFAState>();
        Iterator<NFAState> it = Q.iterator();

        while(it.hasNext())
        {
            NFAState insertState = it.next();
            states.add(insertState);
        }
        return states;
    }

    @Override
    public Set<? extends State> getFinalStates() 
    {
        LinkedHashSet<NFAState> finalStates = new LinkedHashSet<NFAState>();
        Iterator<NFAState> it = Q.iterator();

        while(it.hasNext())
        {
            NFAState tempState = it.next();

            if(tempState.isFinalState() == true)
            {
                finalStates.add(tempState);
            }
        }
        return finalStates;
    }

    @Override
    public State getStartState() 
    {
        NFAState startState = null;
        Iterator<NFAState> it = Q.iterator();

        while(it.hasNext())
        {
            NFAState tempState = it.next();
            if(tempState.isStartState() == true)
            {
                startState = tempState;
            }
        }
        return startState;
    }

    @Override
    public Set<Character> getABC() 
    {
        return this.alphabet;
    }

    @Override
    public DFA getDFA() 
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) 
    {
        return from.getToState(onSymb);
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) 
    {
        Set<NFAState> returnVal = new LinkedHashSet<>();
        returnVal.add(s);
        Set<NFAState> toStates = s.getEStates();
        addVisitState(s);

        if(toStates.isEmpty())
        {
            return returnVal;
        }

        else
        {
            for(NFAState state : toStates)
            {
                if(!checkVisitState(state))
                {
                    returnVal.addAll(eClosure(state));
                }
            }
            visit.clear();
            return returnVal;
        }
    }
    
    public boolean checkVisitState(NFAState s)
    {
        return visit.contains(s);
    }

    private void addVisitState(NFAState s)
    {
        visit.add(s);
    }

    private boolean dfaStateExists(Set<NFAState> checkingStates, DFA dfa)
    {
        boolean returnVal = false;
        Set<DFAState> dfaStates = dfa.getStates();

        for(DFAState dfaState : dfaStates)
        {
            if(dfaState.getName().equals(checkingStates.toString()))
            {
                returnVal = true;
                return returnVal;
            }
        }
        
        return returnVal;
    }
}