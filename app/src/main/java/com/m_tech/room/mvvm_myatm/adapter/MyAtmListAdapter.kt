import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.m_tech.room.mvvm_myatm.R
import com.m_tech.room.mvvm_myatm.model.ATMTableModel
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import kotlin.collections.ArrayList


class MyAtmListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private val TAG: String = "AppDebug"

    private var items: List<ATMTableModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is BlogViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<ATMTableModel>){
        items = blogList
    }

    class BlogViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val withdraw_amount = itemView.txt_item_atm_amount_balance
        val rs100 = itemView.txt_item_txt_rs100
        val rs200 = itemView.txt_item_txt_rs200
        val rs500 = itemView.txt_item_txt_rs500
        val rs2000 = itemView.txt_item__txt_rs2000

        fun bind(ATMTableModel: ATMTableModel){

            withdraw_amount.setText(ATMTableModel.Withdraw_amount.toString())
            rs100.setText(ATMTableModel.Rs100.toString())
            rs200.setText(ATMTableModel.Rs200.toString())
            rs500.setText(ATMTableModel.Rs500.toString())
            rs2000.setText(ATMTableModel.Rs2000.toString())

        }

    }

}