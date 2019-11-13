import React from "react";

import Item from "./Item";

export default function EmptyState({title}) {
    return (
        <>
            <h3 style={styles.heading}>{title}</h3>
            <div className="list-group d-flex w-100 justify-content-between align-items-center">
                <h5> Belum ada item yang dipilih </h5>
                <small>Klik salah satu item di daftar menu</small>
            </div>
            
        </>
    );
}

const styles = {
    heading: {
        fontFamily: "courier new"
    }
}