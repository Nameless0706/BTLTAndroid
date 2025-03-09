package listView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customadapters.R;

import java.util.List;

public class MonHocAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<MonHoc> monHocList;

    public MonHocAdapter(Context context, int layout, List<MonHoc> monHocList) {
        this.context = context;
        this.layout = layout;
        this.monHocList = monHocList;
    }

    @Override
    public int getCount() {
        return monHocList.size(); //lấy kích
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        //gọi view chứa layout
//        convertView = inflater.inflate(layout,null);
//        //ánh xạ view
//        TextView textName = (TextView) convertView.findViewById(R.id.textName);
//        TextView textDesc = (TextView) convertView.findViewById(R.id.textDesc);
//        ImageView imagePic = (ImageView) convertView.findViewById(R.id.imagePic);
//
//        MonHoc monHoc = monHocList.get(position);
//        textName.setText(monHoc.getName());
//        textDesc.setText(monHoc.getDesc());
//        imagePic.setImageResource(monHoc.getPic());
//        //trả về view
//        return convertView;
//
//    }

    private class ViewHolder{
        TextView textName,textDesc;
        ImageView imagePic;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //khởi tạo viewholder
        ViewHolder viewHolder;
        //lấy context
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //gọi view chứa layout
            view = inflater.inflate(layout, null);
            //ánh xạ view
            viewHolder = new ViewHolder();
            viewHolder.textName = (TextView) view.findViewById(R.id.textName2);
            viewHolder.textDesc = (TextView) view.findViewById(R.id.textDesc2);
            viewHolder.imagePic = (ImageView) view.findViewById(R.id.imagePic2);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //gán giá trị
        MonHoc monHoc = monHocList.get(i);
        viewHolder.textName.setText(monHoc.getName());
        viewHolder.textDesc.setText(monHoc.getDesc());
        viewHolder.imagePic.setImageResource(monHoc.getPic());
        //trả về view
        return view;

    }
}
