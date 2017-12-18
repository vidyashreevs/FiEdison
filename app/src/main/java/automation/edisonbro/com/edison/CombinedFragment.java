package automation.edisonbro.com.edison;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import automation.edisonbro.com.edison.model.QQ;
import automation.edisonbro.com.edison.sdlv.src.main.java.com.yydcdut.sdlv.Menu;
import automation.edisonbro.com.edison.sdlv.src.main.java.com.yydcdut.sdlv.MenuItem;
import automation.edisonbro.com.edison.sdlv.src.main.java.com.yydcdut.sdlv.SlideAndDragListView;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CombinedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CombinedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CombinedFragment extends Fragment implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener,
        SlideAndDragListView.OnDragListener, SlideAndDragListView.OnSlideListener,
        SlideAndDragListView.OnMenuItemClickListener, SlideAndDragListView.OnItemDeleteListener, ViewPager.OnPageChangeListener{





    private static final String TAG = CombinedFragment.class.getSimpleName();
    DataBaseHandler1 db= null;
    private List<Menu> mMenuList;
    private List<QQ> mQQList;
    private SlideAndDragListView<QQ> mListView;
    private Toast mToast;

    String roomnamesdb[];
    String imgnamesdb[];
    int startpos,endos;
    GridView gridView,gridView1;
    LinearLayout linear;
View view;
    ListviewAdapter ladapter;

    public Integer[] images5={R.mipmap.ac_grid,R.mipmap.switchboard_grid,};
    private ImageView selectedView = null;
    Integer []old = new Integer[1];
    //////////////////////////////////////////
    ViewPager mViewPager;
    private CustomPagerAdapter mmmAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    int layoutes[]={R.layout.rgb,R.layout.aquarium};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CombinedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CombinedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CombinedFragment newInstance(String param1, String param2) {
        CombinedFragment fragment = new CombinedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


         view = inflater.inflate(R.layout.fragment_combined,container, false);
       // return inflater.inflate(R.layout.fragment_combined, container, false);
//return view;

        linear = (LinearLayout) view.findViewById(R.id.linear);



      //  gridtest = (GridView) view.findViewById(R.id.testgridView);
      //  gridtest.setAdapter(new ImageTest(getActivity().getApplicationContext(),0));


        //startZoomOutAnimation(linear);
/*
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
        //  mAdapter = new CustomPagerAdapter(this, mResources);
        mmmAdapter = new CustomPagerAdapter(getActivity().getApplicationContext(),layoutes);
        mViewPager.setAdapter(mmmAdapter);
        mViewPager.setCurrentItem(0);
//        mViewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) getActivity().getApplicationContext());*/

//        setPageViewIndicator();

        startZoominAnimation(linear);
        gridView = (GridView) view.findViewById(R.id.gridView);


      // gridView1 = (GridView) view.findViewById(R.id.gridaqua);

        int index=0;
        gridView.setAdapter(new ImageAdapter(getActivity().getApplicationContext(),index));
      // gridView1.setAdapter(new ImageAdapter_fish(getActivity().getApplicationContext()));





        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Aquarium fr = null;
              //  fr = new Aquarium();
              //  FragmentManager fm = getChildFragmentManager();



              //  Aquarium fraqua = null;
                switch(position) {
                    case 0:

                        Toast.makeText(getActivity().getApplicationContext(), "Position" + position, Toast.LENGTH_SHORT).show();


                      // FragmentManager fm = getFragmentManager();
                        FragmentManager fm_aqu = getChildFragmentManager();
                       FragmentTransaction child_fragmentTransaction = fm_aqu.beginTransaction();
                        Aquarium fraqua=new Aquarium();
                        child_fragmentTransaction.replace(R.id.fragment_place,  fraqua);
                        child_fragmentTransaction.addToBackStack(null);
                        child_fragmentTransaction.commit();
                        break;


                    case 1:

                        Toast.makeText(getActivity().getApplicationContext(), "Position"+position, Toast.LENGTH_SHORT).show();
                       Rgb fr = new Rgb();
                        android.support.v4.app.FragmentManager fm1 = getFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
                        fragmentTransaction1.replace(R.id.fragment_place, fr);
                        fragmentTransaction1.addToBackStack(null);
                        fragmentTransaction1.commit();




                 }
////////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////////////
             /*   Fragment fr = null;
                switch(position) {
                    case 0:

                        Toast.makeText(getActivity().getApplicationContext(), "Position" + position, Toast.LENGTH_SHORT).show();

                       *//**//* fr = new Aquarium();
                        android.support.v4.app.FragmentManager fm = getFragmentManager();

                        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();

                        fragmentTransaction.replace(R.id.fragment_place,  fr);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();*//**//*
                        break;


                    case 1:

                        Toast.makeText(getActivity().getApplicationContext(), "Position"+position, Toast.LENGTH_SHORT).show();
                       *//**//* fr = new Rgb();
                        android.support.v4.app.FragmentManager fm1 = getFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
                        fragmentTransaction1.replace(R.id.fragment_place, fr);
                        fragmentTransaction1.addToBackStack(null);
                        fragmentTransaction1.commit();*//**//*


*/

               // }
            }
        });




        db=new DataBaseHandler1(getActivity().getApplicationContext());
        //  db.opendb();
        db.getWritableDatabase();
        int rowcount=db.numberOfRows();
        if(rowcount<=0) {
            db.insertData("Entrance","1","2","tentrance01");
            db.insertData("Conference", "2", "4","tconference01");
            db.insertData("Reception", "3", "3","treception01");
            db.insertData("Bedroom", "4", "0","tbedroom01");
            db.insertData("Diningroom", "5", "1","tdiningroom01");

        }

        initData();
        initMenu();
        initUiAndListener();
        //    pullout();





        mToast = Toast.makeText(getActivity().getApplicationContext(), "", Toast.LENGTH_SHORT);

        return view;
    }



    public void fetchposi(int size){
        int newpositionslist[]=db.getposvalues(size);

        for(int j=0;j<newpositionslist.length;j++){

            Log.d("newpositionslist" , ""+newpositionslist[j]);

        }

    }

    public void fetchrooms(){
        List<String> roomlist=db.getData1();
        roomnamesdb=roomlist.toArray(new String[roomlist.size()]);
    }

    public void fetchimgnames(){
        List<String> imglist=db.getimagenames();
        imgnamesdb=imglist.toArray(new String[imglist.size()]);
    }

    public void initData() {

        fetchrooms();
        fetchimgnames();

        fetchposi(roomnamesdb.length);

        for(int j=0;j<roomnamesdb.length;j++){

            Log.d("test","room"+j+"  "+roomnamesdb[j]);


        }


        for(int j=0;j<imgnamesdb.length;j++){

            Log.d("test","imgname"+j+"  "+imgnamesdb[j]);


        }


        // int imgname[] = new int[imgnamesdb.length];
        mQQList = new ArrayList<>();

        for(int k=0;k<imgnamesdb.length;k++){

            String mDrawableName = imgnamesdb[k];
            int resID = getResources().getIdentifier(mDrawableName , "drawable", getActivity().getApplicationContext().getPackageName());
            //  imgname[k] = Integer.parseInt("R.drawable."+imgnamesdb[k]);
            mQQList.add(new QQ("", roomnamesdb[k],"",resID));


          /*  int id = getResources()
                    .getIdentifier("yourpackagename:drawable/" + imgname, null, null);*/

        }




    }

    public void initMenu() {
        mMenuList = new ArrayList<>(2);
        Menu menu0 = new Menu(true, 0);
        menu0.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn2_width))
                .setBackground(new ColorDrawable(Color.RED))
                .setText("DELETE")
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .setTextColor(Color.WHITE)
                .setTextSize(10)
                .build());

        mMenuList.add(menu0);
        // mMenuList.add(menu1);
    }

    public void initUiAndListener() {
        mListView = (SlideAndDragListView) view.findViewById(R.id.lv_edit);
        mListView.setMenu(mMenuList);
        ladapter=new ListviewAdapter(getContext(),mQQList);
        mListView.setAdapter(ladapter);

        mListView.setOnItemLongClickListener(this);
        mListView.setOnDragListener(this, mQQList);
        mListView.setOnItemClickListener(this);
        //   mListView.setOnSlideListener(this);
        //   mListView.setOnMenuItemClickListener(this);
        mListView.setOnItemDeleteListener(this);
        // mListView.setDivider(new ColorDrawable(Color.GRAY));
        //  mListView.setDividerHeight(1);
    }



  /*  private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mQQList.size();
        }

        @Override
        public Object getItem(int position) {
            return mQQList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mQQList.get(position).hashCode();
        }

        @Override
        public int getItemViewType(int position) {
            if (mQQList.get(position).isQun()) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CustomViewHolder cvh = null;
            if (convertView == null) {
                cvh = new CustomViewHolder();
                convertView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.item_qq, null);
                cvh.imgLogo = (ImageView) convertView.findViewById(R.id.img_item_qq);
                // cvh.txtName = (TextView) convertView.findViewById(R.id.txt_item_qq_title);
               cvh.txtContent = (TextView) convertView.findViewById(R.id.txt_item_qq_title);
                convertView.setTag(cvh);
            } else {
                cvh = (CustomViewHolder) convertView.getTag();
            }
            QQ item = (QQ) this.getItem(position);
            cvh.imgLogo.setImageResource(item.getDrawableRes());
           cvh.txtContent.setText(item.getContent());
            if (getItemViewType(position) == 0) {
                // cvh.txtName.setText(item.getName());
            } else {
                // cvh.txtName.setText("(QQ群) " + item.getName() + "(" + item.getQunNumber() + ")");
            }
            return convertView;
        }

        class CustomViewHolder {
            public ImageView imgLogo;
            //public TextView txtName;
            public TextView txtContent;
        }

    };*/

    @Override
    public void onDragViewStart(int position) {
        mToast.setText("onDragViewStart   position--->" + position);
        mToast.show();
        Log.i(TAG, "onDragViewStart   " + position);
        startpos=position;
    }


    public void onDragViewMoving(int position) {
//        Toast.makeText(DifferentActivity.this, "onDragViewMoving   position--->" + position, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onDragViewMoving   " + position);
    }


    public void onDragViewDown(int position) {
        mToast.setText("onDragViewDown   position--->" + position);
        mToast.show();
        Log.i(TAG, "onDragViewDown   " + position);
        endos=position;

        //  Log.i(TAG, "startpos   " + startpos +"end pos"+endos);

        mToast.setText("startpos   " + startpos +"end pos"+endos);
        mToast.show();
        int listsize=mQQList.size();
        if(startpos>endos){
            for(int l=endos;l<startpos;l++) {
                // boolean insfg= db.updateroompos_whr_nameis(roomnamesdb[endos],""+(endos+1));
                boolean insfg= db.updateroompos_whr_nameis(roomnamesdb[l],""+(l+1));
                // new_pos_array[y]=
            }
            db.updateroompos_whr_nameis(roomnamesdb[startpos],""+(endos));
        }else{

            for(int l=startpos+1;l<=endos;l++) {
                boolean insfg= db.updateroompos_whr_nameis(roomnamesdb[l],""+(l-1));
                // new_pos_array[y]=
            }
            db.updateroompos_whr_nameis(roomnamesdb[startpos],""+(endos));
        }


    }


    public void onSlideOpen(View view, View parentView, int position, int direction) {
        mToast.setText("onSlideOpen   position--->" + position + "  direction--->" + direction);
        mToast.show();
        Log.i(TAG, "onSlideOpen   " + position);
    }


    public void onSlideClose(View view, View parentView, int position, int direction) {
        mToast.setText("onSlideClose   position--->" + position + "  direction--->" + direction);
        mToast.show();
        Log.i(TAG, "onSlideClose   " + position);
    }


    public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
        Log.i(TAG, "onMenuItemClick   " + itemPosition + "   " + buttonPosition + "   " + direction);
        int viewType = ladapter.getItemViewType(itemPosition);
        switch (viewType) {
            case 0:
                return clickMenuBtn0(buttonPosition, direction);
            case 1:
                return clickMenuBtn1(buttonPosition, direction);
            default:
                return Menu.ITEM_NOTHING;
        }
    }

    private int clickMenuBtn0(int buttonPosition, int direction) {
        switch (direction) {
            case MenuItem.DIRECTION_LEFT:
                switch (buttonPosition) {
                    case 0:
                        return Menu.ITEM_SCROLL_BACK;
                }
                break;
            case MenuItem.DIRECTION_RIGHT:
                switch (buttonPosition) {
                    case 0:
                        return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
                    case 1:
                        return Menu.ITEM_NOTHING;
                    case 2:
                        return Menu.ITEM_SCROLL_BACK;
                }
        }
        return Menu.ITEM_NOTHING;
    }

    private int clickMenuBtn1(int buttonPosition, int direction) {
        switch (direction) {
            case MenuItem.DIRECTION_LEFT:
                switch (buttonPosition) {
                    case 0:
                        return Menu.ITEM_SCROLL_BACK;
                }
                break;
            case MenuItem.DIRECTION_RIGHT:
                switch (buttonPosition) {
                    case 0:
                        return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
                    case 1:
                        return Menu.ITEM_SCROLL_BACK;
                }
        }
        return Menu.ITEM_NOTHING;
    }


    public void onItemDeleteAnimationFinished(View view, int position) {
        mQQList.remove(position - mListView.getHeaderViewsCount());
        ladapter.notifyDataSetChanged();
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //mListView.getId()
        mToast.setText("onItemClick   position--->" + position);
        mToast.show();
        Log.i(TAG, "onItemClick   " + position);

        if(position==0)
        {
            int index=position;
            gridView.setAdapter(new ImageAdapter(getActivity().getApplicationContext(),index));
        }
        else if(position==1)
        {

            int index=position;
            gridView.setAdapter(new ImageAdapter(getActivity().getApplicationContext(),index));
        }
        else if(position==2)
        {
            int index=position;
            gridView.setAdapter(new ImageAdapter(getActivity().getApplicationContext(),index));
        }
        else if(position==3)
        {
            int index=position;
            gridView.setAdapter(new ImageAdapter(getActivity().getApplicationContext(),index));
        }
        else if(position==4)
        {
            int index=position;
            gridView.setAdapter(new ImageAdapter(getActivity().getApplicationContext(),index));
        }





    }


    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        mToast.setText("onItemLongClick   position--->" + position);
        mToast.show();
        Log.i(TAG, "onItemLongClick   " + position);


        return false;
    }

///////////////////////////////////////////////////////////////////
public void startZoomOutAnimation(LinearLayout linear) {
    Animation zoomOutAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.zoom_out_animation);
    linear.startAnimation(zoomOutAnimation);
}


    public void startZoominAnimation(LinearLayout linear) {

        Animation zoomOutAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.zoom_in_animation);
        linear.startAnimation(zoomOutAnimation);

    }








    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    //////////////////////////////////////////////////





    private void setPageViewIndicator() {



        Log.d("###setPageViewIndicator", " : called");



        dotsCount = ladapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity().getApplicationContext());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mViewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditemdot));
    }


    public void onClick(View v) {


    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    public void onPageSelected(int position) {

        //  Intent i11 =new Intent(MainActivity.this,One.class);
        //  startActivity(i11);


        Log.d("###onPageSelected, pos ", String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditemdot));

        if (position + 1 == dotsCount) {

        } else {

        }
    }


    public void onPageScrollStateChanged(int state) {


    }
}
