package com.finsmart.app.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.finsmart.app.R
import com.finsmart.app.ui.auth.AuthActivity
import com.finsmart.app.util.PreferenceManager
import com.google.android.material.button.MaterialButton

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorsContainer: LinearLayout
    private lateinit var btnNext: MaterialButton
    private lateinit var btnSkip: MaterialButton
    private lateinit var adapter: OnboardingAdapter
    private lateinit var preferenceManager: PreferenceManager

    private val onboardingItems = listOf(
        OnboardingItem(
            R.string.onboarding_title_1,
            R.string.onboarding_desc_1,
            R.drawable.ic_dashboard
        ),
        OnboardingItem(
            R.string.onboarding_title_2,
            R.string.onboarding_desc_2,
            R.drawable.ic_investments
        ),
        OnboardingItem(
            R.string.onboarding_title_3,
            R.string.onboarding_desc_3,
            R.drawable.ic_education
        ),
        OnboardingItem(
            R.string.onboarding_title_4,
            R.string.onboarding_desc_4,
            R.drawable.ic_assistant
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        preferenceManager = PreferenceManager(this)
        
        // Inicializar vistas
        viewPager = findViewById(R.id.viewPager)
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        btnNext = findViewById(R.id.btnNext)
        btnSkip = findViewById(R.id.btnSkip)
        
        // Configurar adaptador
        adapter = OnboardingAdapter(onboardingItems)
        viewPager.adapter = adapter
        
        // Configurar indicadores
        setupIndicators()
        setCurrentIndicator(0)
        
        // Configurar listener para cambio de página
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                
                // Cambiar texto del botón en la última página
                if (position == onboardingItems.size - 1) {
                    btnNext.text = getString(R.string.get_started)
                } else {
                    btnNext.text = getString(R.string.next)
                }
            }
        })
        
        // Configurar botones
        btnNext.setOnClickListener {
            if (viewPager.currentItem + 1 < onboardingItems.size) {
                viewPager.currentItem += 1
            } else {
                finishOnboarding()
            }
        }
        
        btnSkip.setOnClickListener {
            finishOnboarding()
        }
    }
    
    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(onboardingItems.size)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.indicator_inactive
                )
            )
            indicators[i]?.layoutParams = layoutParams
            indicatorsContainer.addView(indicators[i])
        }
    }
    
    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
    
    private fun finishOnboarding() {
        // Marcar que el onboarding ha sido completado
        preferenceManager.setFirstLaunchCompleted()
        
        // Navegar a la pantalla de autenticación
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}

data class OnboardingItem(
    val titleResId: Int,
    val descriptionResId: Int,
    val imageResId: Int
)
