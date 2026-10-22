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
        if (empty())
        {
            throw new StackUnderflowException("Stack is empty");
        }
        return elements.getInfo();
    }

    public T pop() throws StackUnderflowException
    {
        if (empty())
        {
            throw new StackUnderflowException("Stack is empty");
        }
        T item = elements.getInfo();
        elements = elements.getLink();
        size--;
        return item;
    }

    public T push(T item)
    {
        LLNode<T> newLink = new LLNode<>(item);
        newLink.setLink(elements);
        elements = newLink;
        size++;
        return item;
    }

    public void clear()
    {
        elements = null;
        size = 0;
    }

    public int search(Object item)
    {
        LLNode<T> current = elements;
        for (int position = 1; current != null; position++)
        {
            if (item == null ? current.getInfo() == null : item.equals(current.getInfo()))
            {
                return position;
            }
            current = current.getLink();
        }
        return -1;
    }



}
