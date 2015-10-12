package poulsen.Model;

/**
 * Created by Mads on 09-10-2015.
 */

public interface EasyObservable<T> {

    void addListener(OnChangeListener<T> listener);
    void removeListener(OnChangeListener<T> listener);

}

