package com.alimaisam.githubsearch

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.alimaisam.githubsearch.Adapter.RepoList
import com.alimaisam.githubsearch.Helper.EndPoints
import com.alimaisam.githubsearch.Model.Repo
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_repo_list.*
import org.json.JSONException
import org.json.JSONObject

class RepoListActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var repoList: MutableList<Repo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        val type = intent.getIntExtra("Type", 0)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val txtSearch = findViewById<EditText>(R.id.txtSearch);
        val btnSearch = findViewById<Button>(R.id.btnSearch);
        btnSearch.setOnClickListener {
            hideSoftKeyBoard(this@RepoListActivity, it)
            progressBar.visibility = View.VISIBLE
            loadRepos(type, txtSearch.text);
        }

        listView = findViewById<ListView>(R.id.lvRepos)
        repoList = mutableListOf<Repo>()

        when (type) {
            0 -> {
                txtSearch.hint = "Search Language"
            }
            1 -> {
                txtSearch.hint = "Search Topic"
            }
            2 -> {
                txtSearch.visibility = View.GONE
                btnSearch.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                loadReport()
            }
        }
    }

    private fun loadRepos(type: Int, search: Editable?) {
        val urlBuilder = when (type) {
            0 -> EndPoints.URL_SEARCH_BY_LANGUAGE + search
            1 -> EndPoints.URL_SEARCH_BY_TOPIC + search
            else -> EndPoints.URL_REPORT
        }

        val stringRequest = StringRequest(
            Request.Method.GET,
            urlBuilder,
            Response.Listener<String> { response ->
                try {
                    progressBar.visibility = View.GONE
                    val obj = JSONObject(response)
                    val array = obj.getJSONObject("data").getJSONArray("items")

                    for (i in 0 until array.length()) {
                        val objectRepo = array.getJSONObject(i)
                        val repo = Repo(
                            objectRepo.getString("name"),
                            objectRepo.getString("html_url"),
                            objectRepo.getString("description")

                        )
                        repoList!!.add(repo)
                        val adapter = RepoList(this, repoList!!)
                        listView!!.adapter = adapter
                    }
                } catch (e: JSONException) {
                    progressBar.visibility = View.GONE
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError ->
                progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
            })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add<String>(stringRequest)
    }

    private fun loadReport() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            EndPoints.URL_REPORT,
            Response.Listener<String> { response ->
                try {
                    progressBar.visibility = View.GONE
                    val obj = JSONObject(response)
                    val array = obj.getJSONArray("data")

                    for (i in 0 until array.length()) {
                        val objectRepo = array.getJSONObject(i)

                        val searchType = if (objectRepo.getString("search_url").startsWith("/search/repo/topic")) "Topic"
                                                else "Language"

                        val repo = Repo(
                            searchType,
                            objectRepo.getString("search_url"),
                            ""
                        )
                        repoList!!.add(repo)
                    }

                    repoList?.reverse()
                    val adapter = RepoList(this, repoList!!)
                    listView!!.adapter = adapter
                } catch (e: JSONException) {
                    progressBar.visibility = View.GONE
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError ->
                progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
            })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add<String>(stringRequest)
    }

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }
}