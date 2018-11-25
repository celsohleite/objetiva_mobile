package zi.objetivamobile.imobile;

import java.util.EventListener;

import zi.objetivamobile.sync.CategoriaSync;

/**
 * Created by leite on 22/07/2018.
 */

public interface IActionsListener<T, L> extends EventListener{

    void onErrorBusiness(T type, String errorMessage);

    void onErrorBusiness(Throwable error);

    void doResultBusiness(L type, Object object);


}
