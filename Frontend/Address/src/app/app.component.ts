import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['style.scss'],
})
export class AppComponent implements OnInit {
  showForm = false;
  isEdit: boolean = false;  
  editId: number | null = null;  

  newMember = {
    fullName: '',
    address: '',
    state: '',
    city: '',
    zip: '',
    number: ''
  };
  list: any[] = [];

  constructor() {}

  ngOnInit(): void {
    this.fetchInfo();
  }

  async fetchInfo() {
    try {
      const response = await fetch('http://localhost:8080/all');
      if (!response.ok) {
        throw new Error("Connection was not ok");
      }
      this.list = await response.json();
      console.log("Data received:", this.list);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  }

  async addMember() {
    try {
      const response = await fetch('http://localhost:8080/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.newMember)
      });

      if (!response.ok) {
        throw new Error("Connection was not ok");
      }

      console.log("Member added successfully!");
      this.fetchInfo();

     
      this.showForm = false;
      this.newMember = { fullName: '', address: '', state: '', city: '', zip: '', number: '' };

    } catch (error) {
      console.log(error);
    }
  }

  async deleteMember(id: number) { 
    if (!confirm("Are you sure")) return;

    try {
      const response = await fetch(`http://localhost:8080/delete/${id}`, {
        method: 'DELETE'
      });

      if (!response.ok) {
        throw new Error("Failed to delete member");
      }

      console.log("Member deleted successfully!");
      this.fetchInfo();

    } catch (error) {
      console.log(error);
    }
  }

  editMember(member: any) {  
    this.isEdit = true;
    this.showForm = true;
    this.editId = member.id;

   
    this.newMember = { ...member };
  }

  async updateMember() {
    if (this.editId === null) return;

    try {
      const response = await fetch(`http://localhost:8080/update/${this.editId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.newMember)
      });

      if (!response.ok) {
        throw new Error("Failed to update member");
      }

      console.log("Member updated successfully!");
      this.fetchInfo();

    
      this.isEdit = false;
      this.showForm = false;
      this.editId = null;
      this.newMember = { fullName: '', address: '', state: '', city: '', zip: '', number: '' };

    } catch (error) {
      console.log(error);
    }
  }
}
