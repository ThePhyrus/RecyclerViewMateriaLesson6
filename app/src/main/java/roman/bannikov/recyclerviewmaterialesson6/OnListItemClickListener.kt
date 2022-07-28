package roman.bannikov.recyclerviewmaterialesson6

interface OnListItemClickListener { // Нарушаем принципы SOLID((
    fun onItemClick(data: Data)
    fun onAddBtnClick(position: Int)
    fun onRemoveBtnClick(position: Int)
}