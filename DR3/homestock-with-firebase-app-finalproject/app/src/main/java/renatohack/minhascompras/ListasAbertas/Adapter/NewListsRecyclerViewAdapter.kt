package renatohack.minhascompras.ListasAbertas.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_modelo_lista.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import renatohack.minhascompras.Database.Dao.ListDao
import renatohack.minhascompras.Firebase.ListFirebaseDao
import renatohack.minhascompras.Model.Firestore.ListaFirebase
import renatohack.minhascompras.R

class NewListsRecyclerViewAdapter(private val lists: MutableList<ListaFirebase>,
                                  private val listDao: ListDao,
                                  private val actionClick: (id : String) -> Unit ) : RecyclerView.Adapter<NewListsRecyclerViewAdapter.NewListViewHolder>() {

    class NewListViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val nome : TextView = view.textViewNomeListaAdapter
        val botaoDelete : ImageButton = view.imageButtonDeleteAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_modelo_lista, parent, false)

        return NewListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: NewListViewHolder, position: Int) {
        val lista = lists[position]
        val id: String = lists[position].id

        holder.nome.text = lista.name
        holder.botaoDelete.setOnClickListener{
            val itemToRemove : ListaFirebase = lists.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, lists.size)

            ListFirebaseDao().delete(itemToRemove.id)

        }
        holder.itemView.setOnClickListener {itemView ->
            actionClick(id)
        }
    }


}