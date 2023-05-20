package alekseev.pet.labs.Lab3.Segregate;

import alekseev.pet.labs.Lab3.Animals.*;

import java.util.Collection;

public class Segregate {
    public void segregate(Collection<Chordate> srcCollection, Collection<Chordate> collection1,
                                 Collection<Chordate> collection2, Collection<Chordate> collection3) throws ClassCastException{
            srcCollection.forEach((element) -> {
                if (belong(element, Hedgehog.class, Chordate.class))
                    collection1.add(element);
                if (belong(element, Manul.class, Predator.class))
                    collection2.add(element);
                if (belong(element, Lynx.class, Predator.class))
                    collection3.add(element);
            });
    }
    public <T, E> boolean belong(T object, Class<? extends E> bottomClass, Class<E> topClass) throws ClassCastException {
        boolean cond = false;
        while(bottomClass != topClass.getSuperclass()) {
            if(object.getClass() == bottomClass)
                return true;
            bottomClass = (Class<? extends E>) bottomClass.getSuperclass();
        }
        return cond;
    }

}
