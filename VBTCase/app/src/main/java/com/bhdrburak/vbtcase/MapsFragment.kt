package com.bhdrburak.vbtcase

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.math.log

class MapsFragment : Fragment() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var locationManager: LocationManager? = null

    private var lat: Double? = 0.0
    private var long: Double? = 0.0

    private var map : GoogleMap? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */


        /*

        val armada = LatLng(39.912688, 32.810383)
        val dikmen = LatLng(39.895371, 32.841067)
        val tunali = LatLng(39.903405, 32.859650)
        val nataVega = LatLng(39.888259, 32.931533)
        googleMap.addMarker(MarkerOptions().position(armada).title("Armada"))
        googleMap.addMarker(MarkerOptions().position(dikmen).title("dikmen"))
        googleMap.addMarker(MarkerOptions().position(tunali).title("tunali"))
        googleMap.addMarker(MarkerOptions().position(nataVega).title("nataVega"))*/

        /*val markerList = listOf(bahcelievler, armada, dikmen, tunali, nataVega)

        var locationBounds = LatLngBounds.builder()

        for (marker in markerList){
            locationBounds.include(marker)
        }

        val bunds = locationBounds.build()

        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bunds, 100))*/

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(bahcelievler))
        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bahcelievler, 15f))

        map = googleMap

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lat = arguments?.getDouble("lat")
        long = arguments?.getDouble("long")

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        //locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager

        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).apply {
            setMinUpdateDistanceMeters(10f)
            setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
            setWaitForAccurateLocation(true)
        }.build()

        val locationCallBack = object : LocationCallback() {

            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                if (result != null && result.locations.isNotEmpty()) {

                    setMarkerOnMap(LatLng(result.lastLocation?.latitude!!, result.lastLocation?.longitude!!))
                    Log.d(
                        "locationcase",
                        "onLocationResult:  ${result.lastLocation?.latitude} - ${result.lastLocation?.longitude}"
                    )
                }
            }

            override fun onLocationAvailability(p0: LocationAvailability) {
                super.onLocationAvailability(p0)
            }


        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    Log.d("locationcase", "addOnSuccessListener: ${location.latitude} - ${location.longitude}")
                }
            }

        fusedLocationProviderClient.requestLocationUpdates(request, locationCallBack, null)


        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun setMarkerOnMap(latLng: LatLng){


        map?.addMarker(MarkerOptions().position(latLng).title("Marker in newLocation"))

        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }


    override fun onResume() {
        super.onResume()

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    Log.d("locationcase", "addOnSuccessListener: ${location.latitude} - ${location.longitude}")
                }
            }
    }
}