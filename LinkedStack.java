public class LinkedStack<T> implements StackInterface<T> {
    private LLNode<T>  elements;
    private int size;

    public LinkedStack(){
        this.elements = null;
        this.size = 0;
    }

     public int size()
    {
        return size;
    }

    public boolean empty()
    {
        return size == 0;
    }

    public T peek() throws StackUnderflowException
    {
       return elements.getLink().getInfo();
    }

    public T pop() throws StackUnderflowException
    {
        if (empty())
        {
            throw new StackUnderflowException("Stack is empty");
        }
        T item = elements.getLink().getInfo();
        elements.setLink(elements.getLink().getLink());
        size--;
        return item;
    }

    public T push(T item)
    {
        LLNode newLink = new LLNode<T>(item);
        newLink.setLink(elements.getLink());
        elements.setLink(newLink);
        return item;
    }

    public void clear()
    {
        elements.setLink(null);
        size = 0;
    }

    public int search(Object item)
    {
        LLNode temp = elements;
        for(int i = 0; i<size;i++){
            if(temp.getInfo().equals(item)){
                return i;
            }
            temp = temp.getLink();
        }
        return -1;
    }



}
