import java.util.Objects;

@SuppressWarnings("unchecked")
public class ArrayStack<T> implements StackInterface<T>
{

    private T[] elements = (T[]) new Object[4];
    private int size;

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
        return elements[size - 1];
    }

    public T pop() throws StackUnderflowException
    {
        if (empty())
        {
            throw new StackUnderflowException("Stack is empty");
        }
        T item = elements[size - 1];
        elements[size - 1] = null;
        size--;
        if (size <= elements.length / 4 && elements.length > 4)
        {
            T[] newElements = (T[]) new Object[elements.length / 2];
            for (int i = 0; i < size; i++)
            {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        return item;
    }

    public T push(T item)
    {
        if (size == elements.length)
        {
            T[] newElements = (T[]) new Object[elements.length * 2];
            for (int i = 0; i < elements.length; i++)
            {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        elements[size] = item;
        size++;
        return item;
    }

    public void clear()
    {
        for (int i = 0; i < size; i++)
        {
            elements[i] = null;
        }
        size = 0;
    }

    public int search(Object item)
    {
        for (int i = size - 1; i >= 0; i--)
        {
            if (elements[i] != null && elements[i].equals(item))
            {
                return size - i;
            }
        }
        return -1;
    }


}
