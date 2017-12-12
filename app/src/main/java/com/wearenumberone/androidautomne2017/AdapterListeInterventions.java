package v_ed.examen2;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wearenumberone.androidautomne2017.Intervention;

import java.util.ArrayList;

/**
 * Created by kfostine on 2017-10-13.
 */
public class AdapterListeIntervention extends ArrayAdapter<Intervention> {

    private Context context;
    private int layoutItemListe;
    private Resources res;
    ArrayList<Intervention> listeIntervs;

    public AdapterListeJoueur(Context context, int layoutItemListe, ArrayList<Intervention> listeIntervs){
        super(context, layoutItemListe, listeIntervs);
        this.context = context;
        this.layoutItemListe = layoutItemListe;
        res = context.getResources();
        this.listeIntervs = listeIntervs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layoutItemListe, parent, false);
            Intervention intervention = listeIntervs.get(position);
            if(intervention != null){

                TextView textViewNomJoueur = (TextView) view.findViewById(R.id.nom);
                TextView textViewRoleJoueur = (TextView) view.findViewById(R.id.role);
                TextView textViewDateNaissanceJoueur = (TextView) view.findViewById(R.id.dateNaissance);
                TextView textViewLieuNaissanceJoueur = (TextView) view.findViewById(R.id.lieuNaissance);
                TextView textViewTailleJoueur = (TextView) view.findViewById(R.id.taille);
                TextView textViewNumeroJoueur = (TextView) view.findViewById(R.id.numero);
                ImageView imageViewCours = (ImageView) view.findViewById(R.id.icon);

                textViewNomJoueur.setText(intervention.getNom());
                textViewRoleJoueur.setText(intervention.getRole());
                textViewDateNaissanceJoueur.setText(intervention.getDateNaissance());
                textViewLieuNaissanceJoueur.setText(intervention.getLieuNaissance());
                textViewTailleJoueur.setText(intervention.getTaille() + " m");
                textViewNumeroJoueur.setText(intervention.getNumero() + "");
                imageViewCours.setImageResource(intervention.getPhoto());
                
            }
        }
        return view;
    }

    @Override
    public int getCount() {
        return listeIntervs.size();
    }
}
