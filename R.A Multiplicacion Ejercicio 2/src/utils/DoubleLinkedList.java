package utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements List<T> {
    private Nodo<T> root, tail;
    private long lenght;


    public DoubleLinkedList(T value) {
        this.root = new Nodo<>();
        this.tail = new Nodo<>();
        this.lenght = 0;

        if (value != null) {
            Nodo<T> nuevo = new Nodo<>(value);
            root.setRight(nuevo);
            nuevo.setLeft(root);

            nuevo.setRight(tail);
            tail.setLeft(nuevo);
            lenght++;
        }
    }

    public DoubleLinkedList(Nodo<T> nodo) {
        if (nodo != null) {
            lenght++;
            root.setRight(nodo);
            tail.setLeft(nodo);
        }
    }

    public DoubleLinkedList() {
        this((T) null);
    }

    @Override
    public boolean isEmpty() throws NoSuchElementException {
        if (root.getRight() == null && tail.getLeft() == null) {
//            throw new NoSuchElementException("La lista esta vacia, soy el isempty");
            return true;
        }
        return false;
    }

    @Override
    public Nodo<T> getLastElement() {
        if (isEmpty()) {
            return null;
        }
        return tail.getLeft();
    }

    private Nodo<T> getLastElement(Nodo<T> root) {
        if (root.getRight() == null) return root;
        return getLastElement(root.getRight());
    }

    @Override
    public Nodo<T> getPrevElement(T value) {
        try {
            if (isEmpty()) {
                throw new NoSuchElementException("La lista está vacía");
            }
            Nodo<T> tmp = root.getRight();
            while (tmp != null) {
                if (tmp.getValue().equals(value)) {
                    return tmp.getLeft();
                }
                tmp = tmp.getRight();
            }
            throw new NoSuchElementException("No se encontro el elemento, ingresalo y reintenta.");
        } catch (NullPointerException e) {
            System.out.printf("(Probablemente por nulo),  %s \n", e.getMessage());
            return null;
        }
    }

    private Nodo<T> getPrevElement(Nodo<T> root, T value) {
        if (root.getRight() == null) return null;
        if (root.getRight().getValue().equals(value))
            return root; // camino al siguiente, que puede ser el final de la lista, por recursividad entra a la condicion anterior, blindandome de errores
        return getPrevElement(root.getRight(), value);
    }

    @Override
    public long getElement(T element) {
        try {
            isEmpty();
            Nodo<T> tmp = root.getRight();
            int posicion = 0;

            while (tmp != null && tmp != tail) {
                if (tmp.getValue().equals(element)) return posicion + 1;
                tmp = tmp.getRight();
                posicion++;
            }
            throw new NoSuchElementException("No se encontro el elemento");
        } catch (NoSuchElementException e) {
            System.out.printf(" %s ", e.getMessage());
        }
        return -1;
    }

    @Override
    public Nodo<T> getElementAt(int position) {
        try {
            if (position < 0 || position > lenght) throw new IllegalArgumentException("La posicion es invalida");
            if (isEmpty()) throw new NoSuchElementException("La lista esta vacia, ingresa elementos y reintentalo");

            Nodo<T> tmp = root.getRight();
            for (int i = 0; i < position - 1; i++) { // pos -1 para irme una posicion antes, y asi agarrar el right para poder agregar bien
                tmp = tmp.getRight();
            }
            return tmp;
        } catch (NoSuchElementException e) {
            System.out.printf(" %s ", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isThere(T value) {
        try {
            if (isEmpty() || value == null) return false;
            Nodo<T> tmp = root.getRight();
            while (tmp != null) {
                if (tmp.getValue().equals(value)) {
                    return true;
                }
                tmp = tmp.getRight();
            }
        } catch (NoSuchElementException e) {
            System.out.printf(" %s \n", e.getMessage());
        }
        return false;
    }

    @Override
    public void add(T value) {
        if (value != null) {
            Nodo<T> last = getLastElement();
            Nodo<T> new_node = new Nodo<>(value);
            last.setRight(new_node);
            if (lenght != 0) new_node.setLeft(last);
            tail.setLeft(new_node);
            lenght++;

        }
    }

    @Override
    public void add(Nodo<T> node) {
        if (node != null) {
            if (node.getValue() != null) add(node.getValue());
        }
    }


    @Override
    public void addAt(int position, T value) {
        try {
            isEmpty();
            if (position < 0 || position > lenght) throw new IllegalArgumentException("Posicion invalida");
            Nodo<T> new_node = new Nodo<>(value);

            if (position == 0) { // inicio
                new_node.setRight(root.getRight());
                root.getRight().setLeft(new_node);
                new_node.setLeft(null);
                root.setRight(new_node);
            } else if (position == lenght) { // final
                Nodo<T> ultimo = tail.getLeft();
                ultimo.setRight(new_node);
                new_node.setLeft(ultimo);
                new_node.setRight(null);
                tail.setLeft(new_node);
            } else { // en medio
                Nodo<T> ac = root.getRight();
                for (int i = 0; i < position - 1; i++) {
                    ac = ac.getRight();
                }

                Nodo<T> sig = ac.getRight();
                new_node.setRight(sig);
                new_node.setLeft(ac);
                ac.setRight(new_node);
                if (sig != null) sig.setLeft(new_node);
            }
            lenght++;
        } catch (NoSuchElementException e) {
            System.out.printf(" %s \n", e.getMessage());
        }
    }

    @Override
    public void addAfter(int position, Nodo<T> node) {
        try {
            if (position < 0 || position > lenght) throw new IllegalArgumentException("Posicion invalida");
            Nodo<T> tmp = root; // para el valor
            for (int i = 0; i < position - 1; i++) { // pos -1 para irme una posicion antes, y asi agarrar el right para poder agregar bien
                tmp = tmp.getRight();
            }
            node.setRight(tmp.getRight());
            node.setLeft(tmp);
            if (tmp.getRight() != null) {
                tmp.getRight().setLeft(node);
            } else {
                tail.setLeft(node);
            }
            tmp.setRight(node);
            lenght++;
        } catch (NoSuchElementException e) {
            System.out.printf(" %s ", e.getMessage());
        }

    }

    @Override
    public void remove(T value) {
        if (value == null) {
            throw new IllegalArgumentException("No ingrese valores nulos, reintentalo mambicho");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("La lista esta vacia, ingresa mas de 2 valores, y reintentalo");
        }

        Nodo<T> tmp = root.getRight();

        while (tmp != null && tmp != tail) {
            if (tmp.getValue().equals(value)) {
                // si es el primer nodo
                if (tmp == root.getRight()) {
                    root.setRight(tmp.getRight()); // hacemos la conex de root hacia le siguiente 8, 1, 2, 3. Lo conectamos al 1
                    if (root.getRight() == null) { // ya estamos movidos, si a donde nos movimos es nul, (el ultimo de la lista), realisgnamos todo el pedo
                        tail.setLeft(null); // movemos tail a null, a root ya no, porque ya es nulo
                    } else { // si no es el utimo, colocamos nulo a mi 1, en este caso (8, 1, 2, 3) ya estariamos dejando de apuntar al 8
                        root.getRight().setLeft(null);
                    }
                } else {  // si no es el primer nodo, nada mas ajustamos los punteros
                    if (tmp.getLeft() != null) {
                        tmp.getLeft().setRight(tmp.getRight());
                    }
                    if (tmp.getRight() != null) {
                        tmp.getRight().setLeft(tmp.getLeft());
                    }
                }
                // si es el ultimo nodo, ajustamos el puntero de tail y yap
                if (tmp == tail.getLeft()) {
                    tail.setLeft(tmp.getLeft());
                }
                lenght--;
                return; // el return en los metodos void, "matan" el proceso
                // lo vi de stack over flow jasja
                // https://es.stackoverflow.com/questions/368891/para-qu%c3%a9-sirve-return-en-una-funcion-void
            }
            tmp = tmp.getRight(); // Avanzamos al siguiente nodo
        }
        // no se encotro el valor
        throw new IllegalArgumentException("El valor no esta en la lista, agregalo y reintentalo ☝🤓");
    }

    @Override
    public void remove(Nodo<T> node) {
        if (node != null) {
            if (node.getValue() != null) {
                remove(node.getValue());
            }
        }

    }

    @Override
    public void addAfter(int position, T value) {
        try {
            if (position < 0 || position > lenght) throw new IllegalArgumentException("Posicion invalida");
            Nodo<T> tmp = root;
            Nodo<T> new_node = new Nodo<>(value);
            for (int i = 0; i < position; i++) {
                tmp = tmp.getRight();
            }
            new_node.setRight(tmp.getRight());
            new_node.setLeft(tmp);
            if (tmp.getRight() != null) {
                tmp.getRight().setLeft(new_node);
            } else {
                tail.setLeft(new_node);
            }
            tmp.setRight(new_node);
            lenght++;
        } catch (NoSuchElementException e) {
            System.out.printf(" %s ", e.getMessage());
        }
    }

    @Override
    public void addBefore(int position, Nodo<T> node) {
        try {
            isEmpty();
            if (position < 0 || position > lenght) throw new IllegalArgumentException("Posición inválida");
            Nodo<T> tmp = root;
            for (int i = 0; i < position - 1; i++) {
                tmp = tmp.getRight();
            }
            node.setRight(tmp);
            node.setLeft(tmp.getLeft());

            if (tmp.getLeft() != null) {
                tmp.getLeft().setRight(node);
            } else {
                root = node;
            }
            tmp.setLeft(node);
            lenght++;
        } catch (NoSuchElementException e) {
            System.out.printf(" %s ", e.getMessage());
        }
    }

    @Override
    public void addBefore(int position, T value) {
        try {
            isEmpty();
            if (position <= 0 || position > lenght) throw new IllegalArgumentException("Posicion invalida");
            Nodo<T> tmp = root;
            Nodo<T> new_node = new Nodo<>(value);

            for (int i = 0; i < position; i++) {
                tmp = tmp.getRight();
            }
            new_node.setRight(tmp);
            new_node.setLeft(tmp.getLeft());

            if (tmp.getLeft() != null) {
                tmp.getLeft().setRight(new_node);
            } else {
                root = new_node;
            }
            tmp.setLeft(new_node);
            lenght++;
        } catch (NoSuchElementException e) {
            System.out.printf(" %s ", e.getMessage());
        }
    }


    @Override
    public void removeAfter(int position, Nodo<T> node) {
        if (node != null) removeAfter(position, node.getValue());
    }

    @Override
    public void removeAfter(int position, T node) {
        try {
            isEmpty();
            if (position < 0 || position > lenght) throw new IllegalArgumentException("Posición invalida");
            Nodo<T> tmp = root;

            for (int i = 0; i < position; i++) {
                tmp = tmp.getRight();
            }

            Nodo<T> new_node = tmp.getRight();
            tmp.setRight(new_node.getRight());
            new_node.getRight().setLeft(tmp);
            new_node.setLeft(null);
            new_node.setRight(null);
            lenght--;
        } catch (NoSuchElementException e) {
            System.out.printf(" %s \n", e.getMessage());
        }
    }

    @Override
    public void removeBefore(int position, Nodo<T> node) {
        if (node != null || node.getRight() != null) {
            removeBefore(position, node.getValue());
        }
    }

    @Override
    public void removeBefore(int position, T value) {
        try {
            isEmpty();

            if (position < 0 || position > lenght) throw new IllegalArgumentException("Posicion invalida");
            Nodo<T> tmp = root.getRight();

            for (int i = 1; i < position; i++) {
                tmp = tmp.getRight();
            }
            Nodo<T> nodoEliminar = tmp;
            Nodo<T> nodoAntes = nodoEliminar.getLeft();
            Nodo<T> nodoDespues = nodoEliminar.getRight();
            nodoAntes.setRight(nodoDespues);
            nodoDespues.setLeft(nodoAntes);
            nodoEliminar.setLeft(null);
            nodoEliminar.setRight(null);
            lenght--;
        } catch (NoSuchElementException e) {
            System.out.printf(" %s \n", e.getMessage());
        }
    }

    @Override
    public void addStart(Nodo<T> node) {
        if (node.getValue() != null && node != null) {
            addStart(node.getValue());
        } else {
            throw new IllegalArgumentException("Ingrese datos validos");
        }
    }

    @Override
    public void addStart(T value) {
        if (value != null) {
            Nodo<T> tmp = new Nodo<>(value);
            tmp.setRight(root.getRight());
            if (root.getRight() != null) {
                root.getRight().setLeft(tmp);
            } else {
                tail.setLeft(tmp);
            }
            root.setRight(tmp);
            lenght++;
        } else {
            throw new IllegalArgumentException("Coloque un numero valido");
        }

    }

    @Override
    public long lenght(Nodo<T> node) {
        return lenght;
    }

    @Override
    public void removeAll(T value) {
        try {
            isEmpty();
            if (value == null) throw new IllegalArgumentException("Ingrese valores correctos");

            Nodo<T> tmp = root.getRight();
            while (tmp != null) {
                if (tmp.getValue().equals(value)) {
                    Nodo<T> nodoSiguiente = tmp.getRight();

                    if (tmp == root.getRight()) {
                        root.setRight(nodoSiguiente);
                        if (nodoSiguiente != null) {
                            nodoSiguiente.setLeft(null);
                        } else {
                            tail = null;
                        }
                    } else if (tmp == tail) {
                        tail = tmp.getLeft();
                        if (tail != null) {
                            tail.setRight(null);
                        }
                    } else {
                        tmp.getLeft().setRight(tmp.getRight());
                        tmp.getRight().setLeft(tmp.getLeft());
                    }

                    lenght--;
                    tmp = nodoSiguiente;
                } else {
                    tmp = tmp.getRight();
                }
            }

        } catch (NoSuchElementException e) {
            System.out.printf(" %s \n", e.getMessage());
        }
    }

    @Override
    public void removeAll(Nodo<T> nodo) {
        try {
            isEmpty();
            if (nodo == null) throw new IllegalArgumentException("Ingrese valores validos");

            Nodo<T> tmp = root.getRight();
            while (tmp != null) {
                if (tmp.equals(nodo)) {

                    Nodo<T> siguiente = tmp.getRight();

                    if (tmp == root.getRight()) {
                        root.setRight(siguiente);
                        siguiente.setLeft(null);
                    } else if (tmp.getRight() == tail) {
                        tmp.getLeft().setRight(null);
                        tail.setLeft(tmp.getLeft());
                    } else {
                        tmp.getLeft().setRight(tmp.getRight());
                        tmp.getRight().setLeft(tmp.getLeft());
                    }
                    lenght--;
                    tmp = siguiente;
                } else {
                    tmp = tmp.getRight();
                }
            }
        } catch (NoSuchElementException e) {
            System.out.printf(" %s \n", e.getMessage());
        }

    }

    public Iterator<T> right() {
        return new Iterator<T>() {
            Nodo<T> copy = root.getRight(), sub_node;

            @Override
            public boolean hasNext() {
                if (copy == null) return false;
                sub_node = copy;
                copy = copy.getRight();
                return true;
            }

            @Override
            public T next() {
                return sub_node.getValue();
            }
        };
    } // end it

    public Iterator<T> left() {
        return new Iterator<T>() {
            Nodo<T> copy = tail.getLeft(), sub_node;

            @Override
            public boolean hasNext() {
                if (copy == null) return false;
                sub_node = copy;
                copy = copy.getLeft();
                return true;
            }

            @Override
            public T next() {
                return sub_node.getValue();
            }
        };

    }// end it left

    @Override
    public String toString() {
        if (isEmpty()) {
            return "La lista esta vacia, ingresa algo";
        }

        StringBuilder cadena = new StringBuilder(" [ ");
        Nodo<T> tmp = root.getRight();

        while (tmp != null && tmp != tail) {
            cadena.append(tmp.getValue());
            if (tmp.getRight() != tail) {
                cadena.append("| <---> |");
            }
            tmp = tmp.getRight();
        }
        return cadena.append(" ] ").toString();
    }

    public <T extends Number> DoubleLinkedList<Integer> multiply(DoubleLinkedList<T> other) {
        DoubleLinkedList<Integer> result = new DoubleLinkedList<>(); // Lista para almacenar el resultado
        int[] tempResult = new int[(int)(this.lenght + other.lenght)]; // Arreglo temporal para almacenar el resultado

        Nodo<T> current1 = this.root.getRight(); // Primer número (tipo T)
        int index1 = 0;

        // Multiplicamos cada dígito de la primera lista por cada dígito de la segunda lista
        while (current1 != null && current1 != tail) {
            Nodo<T> current2 = other.root.getRight(); // Cambiar a Nodo<T>
            int index2 = 0;

            while (current2 != null && current2 != tail) {
                int product = current1.getValue().intValue() * current2.getValue().intValue(); // Usar intValue() para obtener el valor como int
                tempResult[index1 + index2] += product; // Sumar al índice correspondiente
                current2 = current2.getRight();
                index2++;
            }

            current1 = current1.getRight();
            index1++;
        }

        // Manejar el acarreo y llenar la lista de resultados
        for (int i = 0; i < tempResult.length; i++) {
            if (tempResult[i] != 0) {
                result.add(tempResult[i] % 10); // Agregar el dígito menos significativo
                if (i + 1 < tempResult.length) {
                    tempResult[i + 1] += tempResult[i] / 10; // Acarreo
                }
            }
        }

        // Si la lista resultante está vacía, agregar un 0
        if (result.isEmpty()) {
            result.add(0);
        }

        return result;
    }

}
