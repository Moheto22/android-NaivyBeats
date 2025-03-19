import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.naivybeats.models.municipality.model.Municipality

class MunicipalityAdapter(
    context: Context,
    private val municipalities: List<Municipality>
                         ) : ArrayAdapter<Municipality>(context, android.R.layout.simple_dropdown_item_1line, municipalities) {

    private var filteredList: List<Municipality> = municipalities

    override fun getCount(): Int = filteredList.size

    override fun getItem(position: Int): Municipality? = filteredList[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = filteredList[position].name
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                val filteredList = if (constraint.isNullOrEmpty()) {
                    municipalities // Si no hay restricción, mostrar todos los municipios
                } else {
                    municipalities.filter { it.name.contains(constraint, ignoreCase = true) }
                }
                results.values = filteredList
                results.count = filteredList.size
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredList = results?.values as? List<Municipality> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}