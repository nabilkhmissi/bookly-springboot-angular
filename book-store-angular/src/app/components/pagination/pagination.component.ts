import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {

  @Input() pages: number | null = null;
  active = 0;
  @Output() pageEvent = new EventEmitter<any>();

  ngOnInit(): void {
  }

  changePage(i: number) {
    this.pageEvent.emit(i);
    this.active = i
  }
}
