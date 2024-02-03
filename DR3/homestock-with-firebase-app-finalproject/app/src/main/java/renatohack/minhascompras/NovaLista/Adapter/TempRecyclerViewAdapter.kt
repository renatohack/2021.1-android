package renatohack.minhascompras.NovaLista.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_modelo_elementos.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import renatohack.minhascompras.Database.Dao.TempDao
import renatohack.minhascompras.Firebase.TemporaryDao
import renatohack.minhascompras.Model.Firestore.ElementoTemporario
import renatohack.minhascompras.Model.Room.TempElements
import renatohack.minhascompras.R

class TempRecyclerViewAdapter(val temp : MutableList<ElementoTemporario>,
                              val tempDao : TempDao) : RecyclerView.Adapter<TempRecyclerViewAdapter.TempViewHolder>() {


    class TempViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val item : TextView = view.textViewNomeElemento
        val qtde : TextView = view.textViewQtdElemento
        val preco : TextView = view.textViewPrecoElemento
        val total : TextView = view.textViewTotalPrecoElemento
        val botaoDelete : ImageButton = view.imageButtonDeleteElemento
        val imagemElemento : ImageView = view.imageViewElemento
    }

    override fun getItemCount(): Int {
        return temp.size
    }

    override fun onBindViewHolder(holder: TempViewHolder, position: Int) {
        val elemento = temp[position]

        holder.item.text = elemento.name
        holder.qtde.text = elemento.quantity
        holder.preco.text = elemento.price
        holder.total.text = (elemento.price!!.toFloat()*elemento.quantity!!.toInt()).toString()
        holder.botaoDelete.setOnClickListener {


            val itemToRemove: ElementoTemporario = temp.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, temp.size)

            TemporaryDao.delete(itemToRemove.id)
        }
        Picasso.get()
            .load(elemento.url)
            .resize(200, 200)
            .error(android.R.drawable.ic_menu_report_image)
            .priority(Picasso.Priority.HIGH)
            .into(holder.imagemElemento)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_modelo_elementos, parent, false)

        return TempViewHolder(view)
    }


}