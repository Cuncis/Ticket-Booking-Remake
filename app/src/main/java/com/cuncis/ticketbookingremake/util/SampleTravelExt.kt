package com.cuncis.ticketbookingremake.util

import com.cuncis.ticketbookingremake.data.Travel
import com.cuncis.ticketbookingremake.ui.ticket.ticketdetail.TicketDetailViewModel

object SampleTravelExt {
    fun addData(_viewModel: TicketDetailViewModel) {
        val travel1 = Travel.Place(
            "Candi",
            "Magelang, Yogya",
            "January 22, 2018",
            "10AM to 12 PM",
            "The leaning of the tower of Pisa comes.",
            26L,
            "1 Festival",
            "4 Photo Spots",
            "Wifi Available",
            "Tidak ada penggunaan drone",
            "https://firebasestorage.googleapis.com/v0/b/tiketsaya-57a81.appspot.com/o/Pisa%2Fbgheader_pisa.png?alt=media&token=14d4a47d-5508-4ea2-b95f-0a1142d17819"
        )
        val travel2 = Travel.Place(
            "Monas",
            "Monas, Jakarta",
            "January 22, 2018",
            "10AM to 12 PM",
            "The leaning of the tower of Pisa comes.",
            14L,
            "1 Festival",
            "5 Photo Spots",
            "Wifi Available",
            "Tidak boleh bawa air dan makanan",
            "https://firebasestorage.googleapis.com/v0/b/tiketsaya-57a81.appspot.com/o/Pisa%2Fbgheader_pisa.png?alt=media&token=14d4a47d-5508-4ea2-b95f-0a1142d17819"
        )
        val travel3 = Travel.Place(
            "Pagoda",
            "Shanghai, China",
            "January 22, 2018",
            "10AM to 12 PM",
            "The leaning of the tower of Pisa comes.",
            63L,
            "1 Festival",
            "4 Photo Spots",
            "Wifi Available",
            "The leaning of the tower of Pisa comes.",
            "https://firebasestorage.googleapis.com/v0/b/tiketsaya-57a81.appspot.com/o/Pisa%2Fbgheader_pisa.png?alt=media&token=14d4a47d-5508-4ea2-b95f-0a1142d17819"
        )
        val travel4 = Travel.Place(
            "Pisa",
            "Pisa, Italy",
            "January 21, 2018",
            "10AM to 12 PM",
            "The leaning of the tower of Pisa comes.",
            20L,
            "1 Festival",
            "4 Photo Spots",
            "Wifi Available",
            "Anak berumur 3 Tahun wajib beli tiket.",
            "https://firebasestorage.googleapis.com/v0/b/tiketsaya-57a81.appspot.com/o/Pisa%2Fbgheader_pisa.png?alt=media&token=14d4a47d-5508-4ea2-b95f-0a1142d17819"
        )
        val travel5 = Travel.Place(
            "Sphinx",
            "Pyramid, Mecca",
            "January 22, 2018",
            "10AM to 12 PM",
            "The leaning of the tower of Pisa comes.",
            51L,
            "1 Festival",
            "4 Photo Spots",
            "Wifi Available",
            "Dianjurkan membawa payung sendiri",
            "https://firebasestorage.googleapis.com/v0/b/tiketsaya-57a81.appspot.com/o/Pisa%2Fbgheader_pisa.png?alt=media&token=14d4a47d-5508-4ea2-b95f-0a1142d17819"
        )
        val travel6 = Travel.Place(
            "Torri",
            "Gate, Hongkong",
            "January 21, 2018",
            "10AM to 12 PM",
            "The leaning of the tower of Pisa comes.",
            10L,
            "1 Festival",
            "4 Photo Spots",
            "Wifi Available",
            "Anak berumur 3 Tahun wajib beli tiket.",
            "https://firebasestorage.googleapis.com/v0/b/tiketsaya-57a81.appspot.com/o/Pisa%2Fbgheader_pisa.png?alt=media&token=14d4a47d-5508-4ea2-b95f-0a1142d17819"
        )

        _viewModel.addSampleData(travel1)
        _viewModel.addSampleData(travel2)
        _viewModel.addSampleData(travel3)
        _viewModel.addSampleData(travel4)
        _viewModel.addSampleData(travel5)
        _viewModel.addSampleData(travel6)
    }

}