package wattwise.app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import wattwise.app.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var btnBack = findViewById<Button>(R.id.btn_back)
        var btnAddDevice = findViewById<Button>(R.id.btn_add_device)
        var btnUser = findViewById<Button>(R.id.btn_account)
        var btnDeleteDevice = findViewById<Button>(R.id.btn_delete_device)
        var btnShowDevicesAdded = findViewById<Button>(R.id.btn_show_devices_added)
        var btnGraphicTotal = findViewById<Button>(R.id.btn_graphic_total)
        var btnGraphicDeviceMonth = findViewById<Button>(R.id.btn_grpahic_device_month)



        btnBack.setOnClickListener {
            val intent = Intent(this, IndexActivity::class.java)
            startActivity(intent)
        }

        btnAddDevice.setOnClickListener {
            val intent = Intent(this, AddDeviceActivity::class.java)
            startActivity(intent)
        }
        btnUser.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
        btnShowDevicesAdded.setOnClickListener {
            val intent = Intent(this, ShowDevicesAddedActivity::class.java)
            startActivity(intent)
        }
        btnGraphicTotal.setOnClickListener {
            val intent = Intent(this, ShowGrapichTotalActivity::class.java)
            startActivity(intent)
        }
        btnGraphicDeviceMonth.setOnClickListener {
            val intent = Intent(this, ShowGrapichDeviceMonthActivity::class.java)
            startActivity(intent)
        }

    }
}
