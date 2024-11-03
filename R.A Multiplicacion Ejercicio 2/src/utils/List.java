package utils;

public interface List<T> {
    //    IsEmpty: Verificara si la lista esta vacía
    boolean isEmpty();

    //    getLastElement: Retornara el último elemento de la lista
    Nodo<T> getLastElement();

    //    getPrevElement: Retornara el elemento previo al que estamos buscando
    Nodo<T> getPrevElement(T value);

    //    getElement: Retornara el elemento que estamos buscando
    long getElement(T element);

    //    getElementAt: retornara el elemento que se encuentra en la posición x de la lista
    Nodo<T> getElementAt(int position);

    boolean isThere(T value);

    //    add: Método que agrega un elemento en la lista
    void add(T value);

    void add(Nodo<T> node);

    //    remove: elemento que elimina un elemento de la lista\
    void remove(T value);

    void remove(Nodo<T> node);

    //    AddAt: Método que agrega un elemento en una posición determinada de la lista.
    void addAt(int position, T value);

    //    addAfter: Método que agrega un valor enseguida de un valor especificado
    void addAfter(int position, Nodo<T> node);

    void addAfter(int position, T node);

    //    addBefore: Método que agrega un valor antes de un valor especificado.
    void addBefore(int position, Nodo<T> node);

    void addBefore(int position, T node);

    //    removeAfter: Método que borra un valor después de un elemento especifico
    void removeAfter(int position, Nodo<T> node);

    void removeAfter(int position, T node);

    //    removeBefore: Método que borra un valor después de un elemento especifico
    void removeBefore(int position, Nodo<T> node);

    void removeBefore(int position, T node);

    //    addStart: Método que agrega un elemento al inicio de la lista.
    void addStart(Nodo<T> node);

    void addStart(T value);

    //    Length: Método que retorna la cantidad de nodos en la lista, puede ser un getter
    long lenght(Nodo<T> node);

    //    removeAll: Método que elimina todos los elementos iguales de una lista.
    void removeAll(T value);

    void removeAll(Nodo<T> nodo);

    //    toString: Método que convierte la lista a una cadena.

}
