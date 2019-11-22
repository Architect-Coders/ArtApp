package com.android.leivacourse.artapp



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.leivacourse.artapp.ListaObrasContract.Presenter
import com.android.leivacourse.artapp.data.ListadoObrasRepository


class ListaObrasFragment : Fragment(), ListaObrasContract.View {

    private lateinit var mPresenter : ListaObrasContract.Presenter


    companion object {
        fun newInstance(): ListaObrasFragment = ListaObrasFragment()

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root: View = inflater.inflate(R.layout.fragment_lista_obras, container, false)



        mPresenter.getListadoObras()


        return root
    }


    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }


    override fun setPresenter(presenter: Any?) {
        mPresenter = checkNotNull(presenter as Presenter)
    }

}
