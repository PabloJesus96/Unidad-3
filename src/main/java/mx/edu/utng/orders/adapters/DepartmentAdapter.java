package mx.edu.utng.orders.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.Departments;

/**
 * Created by Husky Siberiano on 23/02/2017.
 */

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> implements View.OnClickListener {
    List<Departments> departmentsList;
    View.OnClickListener listener;

    public DepartmentAdapter(List<Departments> departmentsList){this.departmentsList=departmentsList;}

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public DepartmentAdapter.DepartmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.department_layout, parent, false);
        DepartmentViewHolder holder = new DepartmentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DepartmentAdapter.DepartmentViewHolder holder, int position) {
        holder.tvDepartmentName.setText(departmentsList.get(position).getDeptoName());
        holder.tvDepartmentPhone.setText(departmentsList.get(position).getDeptoPhone());
        holder.ivDepartment.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return departmentsList.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class DepartmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cvDepartment;
        TextView tvDepartmentName;
        TextView tvDepartmentPhone;
        ImageView ivDepartment;
        ImageButton btEditDepartment;
        ImageButton btDeleteDepartment;
        View.OnClickListener listener;

        public DepartmentViewHolder (View view){
            super(view);
            cvDepartment = (CardView)view.findViewById(R.id.cv_department);
            tvDepartmentName  = (TextView)view.findViewById(R.id.tv_department_name);
            tvDepartmentPhone = (TextView)view.findViewById(R.id.tv_department_phone);
            ivDepartment = (ImageView)view.findViewById(R.id.iv_deparment);
            btEditDepartment = (ImageButton)view.findViewById(R.id.bt_edit_department);
            btDeleteDepartment = (ImageButton)view.findViewById(R.id.bt_delete_department);
            btEditDepartment.setOnClickListener(this);
            btDeleteDepartment.setOnClickListener(this);
        }

        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClick(v);
            }
        }
    }
}
