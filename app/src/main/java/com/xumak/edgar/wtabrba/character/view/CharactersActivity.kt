package com.xumak.edgar.wtabrba.character.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.xumak.edgar.wtabrba.R
import com.xumak.edgar.wtabrba.character.CharacterMVP
import com.xumak.edgar.wtabrba.character.model.service.ObjectResponse
import com.xumak.edgar.wtabrba.character.view.callback.OnItemSelectedListener
import com.xumak.edgar.wtabrba.root.App
import kotlinx.android.synthetic.main.character_activity_layout.*
import javax.inject.Inject

class CharactersActivity : AppCompatActivity(), CharacterMVP.View, OnItemSelectedListener{
    @Inject
    lateinit var presenter : CharacterMVP.Presenter
    private lateinit var currentFragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_activity_layout)
        (application as App).component.inject(this)
        presenter.setView(this)

        currentFragment = CharacterListFragment(this, presenter)
        changeFragment(currentFragment)
    }

    private fun changeFragment(currentFragment : Fragment){
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        //checando si un fragment ya existe, si es correcto no es necesario volver a crearlo
        if (manager.findFragmentByTag(currentFragment.javaClass.canonicalName) == null) {
            transaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
            transaction.replace(R.id.frmContainer, currentFragment)
            transaction.addToBackStack("backStack")
            transaction.commitAllowingStateLoss()
        }
    }

    override fun updateCharactersList(charactersList: List<ObjectResponse.Character>) {
        if (currentFragment is CharacterListFragment){
            (currentFragment as CharacterListFragment).updateCharactersList(charactersList)
        }
    }

    override fun showGetCharactersError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(character: ObjectResponse.Character) {
        changeFragment(CharacterDetailFragment(character))
    }
}